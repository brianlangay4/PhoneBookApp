package com.builtin.phonebook;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RadioButton;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AddContactActivity extends AppCompatActivity {


    private List<String> emailDomains;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);


        // Set the status bar to be transparent
        setTransparentStatusBar();

        // Set the navigation bar to be transparent
        setTransparentNavigationBar();



        //new
        emailDomains = new ArrayList<>();

        // Populating the list with  email domains
        emailDomains.add("gmail.com");
        emailDomains.add("qq.com");
        emailDomains.add("123.com");
        emailDomains.add("yahoo.com");


        //code starts here
        final EditText firstNameEditText = findViewById(R.id.firstNameEditText);
        final EditText lastNameEditText = findViewById(R.id.lastNameEditText);
        final MultiAutoCompleteTextView autoCompleteTextView  = findViewById(R.id.emailEditText);
        final EditText phoneNumberEditText = findViewById(R.id.phoneNumberEditText);
        final Spinner genderSpinner = findViewById(R.id.genderSpinner);
        final RadioButton friendRadioButton = findViewById(R.id.friendRadioButton);
        final RadioButton familyRadioButton = findViewById(R.id.familyRadioButton);
        final RadioButton workRadioButton = findViewById(R.id.workRadioButton);
        Button saveButton = findViewById(R.id.saveButton);



        //new auto complete textView concept
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, emailDomains);
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setTokenizer(new CustomTokenizer());

         //extra not used at the moment

        // Set a listener to handle the selected item
        autoCompleteTextView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedDomain = (String) parent.getItemAtPosition(position);
            // Handle the selected domain as needed
        });


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName = firstNameEditText.getText().toString();
                String lastName = lastNameEditText.getText().toString();
                String email = autoCompleteTextView.getText().toString();
                String phoneNumber = phoneNumberEditText.getText().toString();
                String gender = genderSpinner.getSelectedItem().toString();
                String relationship = "";

                if (friendRadioButton.isChecked()) {
                    relationship = "Friend";
                } else if (familyRadioButton.isChecked()) {
                    relationship = "Family";
                } else if (workRadioButton.isChecked()) {
                    relationship = "Work";
                }

                // Check if all required fields are filled
                if (!firstName.isEmpty() && !lastName.isEmpty() && !email.isEmpty() && !phoneNumber.isEmpty()) {
                    // Create a new Contact object
                    Contact newContact = new Contact(firstName, lastName, email, phoneNumber, gender, relationship);

                    // Return the new contact data to MainActivity
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("contact", newContact);
                    setResult(RESULT_OK, resultIntent);
                    finish();
                }
            }
        });
    }
    private void setTransparentStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Color.TRANSPARENT);

            // Set the status bar icons to be dark
            View decorView = getWindow().getDecorView();
            int systemUiVisibilityFlags = decorView.getSystemUiVisibility();
            systemUiVisibilityFlags |= View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR; // This flag sets the status bar icons to be dark
            decorView.setSystemUiVisibility(systemUiVisibilityFlags);
        }

    }
    private void setTransparentNavigationBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.setNavigationBarColor(Color.TRANSPARENT);
            View decorView = getWindow().getDecorView();
            int systemUiVisibilityFlags = decorView.getSystemUiVisibility();
            systemUiVisibilityFlags |= View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
            decorView.setSystemUiVisibility(systemUiVisibilityFlags);
        }
    }
}
