package com.builtin.phonebook;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Contact implements Serializable {
    private long id; // Added ID for database interaction
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String gender;
    private String relationship;

    public Contact(String firstName, String lastName, String email, String phoneNumber, String gender, String relationship) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.relationship = relationship;
    }

    // Getter and setter methods for ID
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    // Getter methods for other fields
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public String getRelationship() {
        return relationship;
    }

    // Setter methods if needed
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    // Save the contact to the database
    public long saveToDatabase(DatabaseHelper dbHelper) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_FIRST_NAME, firstName);
        values.put(DatabaseHelper.COLUMN_LAST_NAME, lastName);
        values.put(DatabaseHelper.COLUMN_EMAIL, email);
        values.put(DatabaseHelper.COLUMN_PHONE_NUMBER, phoneNumber);
        values.put(DatabaseHelper.COLUMN_GENDER, gender);
        values.put(DatabaseHelper.COLUMN_RELATIONSHIP, relationship);

        long id = database.insert(DatabaseHelper.TABLE_CONTACTS, null, values);
        database.close();

        return id;
    }

    // Retrieve all contacts from the database
    public static List<Contact> getAllContacts(DatabaseHelper dbHelper) {
        List<Contact> contactList = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();

        Cursor cursor = database.query(DatabaseHelper.TABLE_CONTACTS,
                null, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Contact contact = cursorToContact(cursor);
            contactList.add(contact);
            cursor.moveToNext();
        }

        cursor.close();
        database.close();

        return contactList;
    }

    private static Contact cursorToContact(Cursor cursor) {
        Contact contact = new Contact(
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_FIRST_NAME)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_LAST_NAME)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_EMAIL)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_PHONE_NUMBER)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_GENDER)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_RELATIONSHIP))
        );

        contact.setId(cursor.getLong(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID)));
        return contact;
    }
}
