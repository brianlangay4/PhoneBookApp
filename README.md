# Phone Book App Documentation

![phoneBookApp](https://github.com/brianlangay4/PhoneBookApp/assets/67788456/b3ded7ce-eee2-4213-bf32-23c54325a70d)

##
#BRIAN BARNABAS LANGAY
2322190027
##

## preview
![0 0 3](https://github.com/brianlangay4/PhoneBookApp/assets/67788456/314ac8b2-a111-4fe1-b0a5-7344cd44ed30)



## Table of Contents
1. **Introduction**
   - Purpose
   - Features
   - Target Audience

2. **User Guide**
   - Overview
   - Adding Contacts
   - Viewing Contacts

3. **Technical Documentation**
   - Architecture
   - Database Schema
   - Code Structure
   - Data Persistence

4. **Future Enhancements**
   - Updates

## 1. Introduction

### Purpose
The "Phone Book" app is designed to assist users in efficiently managing their contacts on Android devices. It provides a user-friendly interface for adding and viewing contacts.

### Features
- Add new contacts with details such as name, email, phone number, gender, and relationship.
- View a list of all saved contacts with their initials and relationship status.
- Navigate to a contact detail page to view.

### Target Audience
The app is suitable for individuals seeking a simple and effective solution for contact management on their Android devices.

## 2. User Guide

### Overview
Upon opening the app, users are presented with a list of their contacts. The main functionalities include adding new contacts and viewing, updating, and deleting existing contacts.

### Adding Contacts
1. Click the '+' button to navigate to the "Add Contact" page.
2. Fill in the required information (first name, last name, email, phone number, gender, and relationship).
3. Click the "Save" button to add the contact.

### Viewing Contacts
- On the main page, users can see a list of contacts with their initials and relationship status.
- Click on a contact to view more details.

## 3. Technical Documentation

### Architecture
The app follows the Model-View-Controller (MVC) architecture.
- **Model:** `Contact` class with SQLite database integration.
- **View:** Activities and layouts for main, add contact, and detail pages.
- **Controller:** MainActivity, AddContactActivity, and ContactDetailActivity manage user interactions.

### Database Schema
- Table: contacts
  - Columns: _id (primary key), first_name, last_name, email, phone_number, gender, relationship

### Code Structure
- `MainActivity`: Manages the main page with contact list.
- `AddContactActivity`: Handles adding new contacts.
- `ContactDetailActivity`: Displays and updates full contact information.
- `ContactAdapter`: Manages RecyclerView for contact list display.
- `Contact`: Model class for contact details and database operations.
- `DatabaseHelper`: Manages SQLite database creation and access.

### Data Persistence
Contact data is persisted using SQLite, ensuring that added contacts remain accessible across app sessions.

## 4. Future Enhancements

- Update 0.0.4


![Large GIF (640x1310)](https://github.com/brianlangay4/PhoneBookApp/assets/67788456/f34dac1e-56d9-44db-945d-e2b374427162)




## Whats new on 0.0.4


- layout update fixes and and final touches including added go button to navigate to main page
- Auto complete to fit in Suggestion list of email domains after thhe user type @ in Email field 

## Auto complete Logic in New Update 
- added a class as hhelper class for indexing access to reach to @ wen user is typing and class is latter used 
- with the given list of domains List<String> emailDomains; we can now add multiple domains to our list  emailDomains.add("gmail.com");
        emailDomains.add("qq.com");
        emailDomains.add("123.com");
- now we use the list into  MultiAutoCompleteTextView that we ahve changed a regular EditText to this 
- then we create array adapter like ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, emailDomains); to get the domains list and access default dropdown pannel from android resource to display the suggestions
- finally we set thhe adapter to our autocomplete textview and setting the custom Tokenizer we created for indexing purpose


- with custom Tokeniser logic Extended explanation  

# CustomTokenizer Class 

## Overview

The `CustomTokenizer` class is a custom implementation of the `MultiAutoCompleteTextView.Tokenizer` interface in Android. It is specifically designed for auto-completion of email domains in a `MultiAutoCompleteTextView`. The tokenizer defines how tokens are identified, located, and terminated based on user input.

## Key Methods

### 1. `findTokenStart(CharSequence text, int cursor)`

- **Purpose**: Finds the start position of a token.
- **Parameters**:
  - `text`: The input text in the `MultiAutoCompleteTextView`.
  - `cursor`: The current cursor position.
- **Return Value**: The start position of the token.

### 2. `findTokenEnd(CharSequence text, int cursor)`

- **Purpose**: Finds the end position of a token.
- **Parameters**:
  - `text`: The input text in the `MultiAutoCompleteTextView`.
  - `cursor`: The current cursor position.
- **Return Value**: The end position of the token.

### 3. `terminateToken(CharSequence text)`

- **Purpose**: Terminates a token.
- **Parameters**:
  - `text`: The input text representing the token.
- **Return Value**: The terminated token.

## Tokenization Logic

1. **Finding the Start of the Token (`findTokenStart` method):**
   - Starts from the cursor position and iterates backward to find the position of the '@' symbol, indicating the start of an email domain token.

2. **Finding the End of the Token (`findTokenEnd` method):**
   - Starts from the cursor position and iterates forward until a space or another '@' symbol is encountered, determining the end of the email domain token.

3. **Terminating the Token (`terminateToken` method):**
   - Determines whether the token needs termination based on the last character. If the last character is a space, the method trims it. If the last character is an '@', the original text is returned unmodified. If the text is an instance of `Spanned`, spans are copied to the new `SpannableString` to maintain formatting.




## Notes

- This tokenizer assumes that email domains are being entered after the '@' symbol.
- The termination logic ensures that unnecessary spaces are trimmed, and the '@' symbol itself does not introduce a separator.

## Summary 

The `CustomTokenizer` class provides a flexible and customizable way to handle tokenization for email domains in a `MultiAutoCompleteTextView`. Its methods can be adapted for other tokenization scenarios based on specific requirements.


To use the `CustomTokenizer` in a `MultiAutoCompleteTextView`, create an instance of the `CustomTokenizer` class and set it as the tokenizer for the `MultiAutoCompleteTextView`.

- After adding the MultiAutoCompleteTextView in xml layout and have CustomTokenizer class


## Usage
```
// Example code on MultiAutoCompleteTextView usage 
MultiAutoCompleteTextView autoCompleteTextView = findViewById(R.id.autoCompleteTextView);
ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, emailDomains);
autoCompleteTextView.setAdapter(adapter);
autoCompleteTextView.setTokenizer(new CustomTokenizer());
```



#### Adding Contacts page

##### Add User Page

Upon clicking the '+' button in the main activity, users are navigated to the "Add User" page, where they can input information for a new contact. The page is designed with simplicity and clarity in mind to facilitate a smooth user experience.

**Layout Overview:**

The "Add User" page features a clean and intuitive layout with input fields for essential contact details. Here's a breakdown of the layout components:

1. **First Name and Last Name:**
   - Two `EditText` fields for users to enter the first and last names of the contact.

   ```xml
   <EditText
       android:id="@+id/editTextFirstName"
       android:layout_width="match_parent"
       android:layout_height="wrap_content"
       android:hint="First Name" />

   <EditText
       android:id="@+id/editTextLastName"
       android:layout_width="match_parent"
       android:layout_height="wrap_content"
       android:hint="Last Name" />
   ```

2. **Email and Phone Number:**
   - Additional `EditText` fields for the email and phone number of the contact.

   ```xml
   <EditText
       android:id="@+id/editTextEmail"
       android:layout_width="match_parent"
       android:layout_height="wrap_content"
       android:hint="Email" />

   <EditText
       android:id="@+id/editTextPhoneNumber"
       android:layout_width="match_parent"
       android:layout_height="wrap_content"
       android:hint="Phone Number" />
   ```

3. **Gender Spinner:**
   - A `Spinner` to select the gender of the contact (options: Male, Female).

   ```xml
   <Spinner
       android:id="@+id/spinnerGender"
       android:layout_width="match_parent"
       android:layout_height="wrap_content"
       android:hint="Select Gender" />
   ```

4. **Relationship Radio Buttons:**
   - Three radio buttons for selecting the relationship status (options: Friend, Family, Work).

   ```xml
   <RadioGroup
       android:id="@+id/radioGroupRelationship"
       android:layout_width="match_parent"
       android:layout_height="wrap_content"
       android:orientation="horizontal">

       <RadioButton
           android:id="@+id/radioButtonFriend"
           android:layout_width="wrap_content"
           android:layout_height="wrap_content"
           android:text="Friend" />

       <RadioButton
           android:id="@+id/radioButtonFamily"
           android:layout_width="wrap_content"
           android:layout_height="wrap_content"
           android:text="Family" />

       <RadioButton
           android:id="@+id/radioButtonWork"
           android:layout_width="wrap_content"
           android:layout_height="wrap_content"
           android:text="Work" />
   </RadioGroup>
   ```

5. **Save Button:**
   - A button to save the entered information and add the new contact.

   ```xml
   <Button
       android:id="@+id/buttonSave"
       android:layout_width="match_parent"
       android:layout_height="wrap_content"
       android:text="Save" />
   ```

**Functionality:**

- Users are required to fill in the first name, last name, email, phone number, select gender, and choose a relationship status before saving.
- The gender selection is made via the spinner, and the relationship status is chosen using radio buttons.
- Clicking the "Save" button triggers the validation process, ensuring that all required fields are filled with valid information.
- Once saved, the user is redirected back to the main activity, and the new contact is displayed in the contact list.

This layout and functionality aim to provide a straightforward and user-friendly means of adding new contacts to the phone book. Users can easily input relevant details, promoting efficient contact management.


