package com.builtin.phonebook;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ContactDetailActivity extends AppCompatActivity {


    ImageView next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);


        // Set the status bar to be transparent
        setTransparentStatusBar();

        // Set the navigation bar to be transparent
        setTransparentNavigationBar();


        //code starts here
        ImageView next = findViewById(R.id.next);
        TextView fullNameTextView = findViewById(R.id.fullNameTextView);
        TextView emailTextView = findViewById(R.id.emailTextView);
        TextView phoneNumberTextView = findViewById(R.id.phoneNumberTextView);
        TextView genderTextView = findViewById(R.id.genderTextView);
        TextView relationshipTextView = findViewById(R.id.relationshipTextView);
        TextView initialsTextView = findViewById(R.id.contactIconTextView);


        //new
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactDetailActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Get the contact object from the intent
        Contact contact = (Contact) getIntent().getSerializableExtra("contact");

        // Display the full contact information
        if (contact != null) {
            fullNameTextView.setText(contact.getFirstName() + " " + contact.getLastName());
            emailTextView.setText(contact.getEmail());
            phoneNumberTextView.setText(contact.getPhoneNumber());
            genderTextView.setText(contact.getGender());
            relationshipTextView.setText(contact.getRelationship());
        }


        // Set the first and last name initials as the icon
        String initials = contact.getFirstName().substring(0, 1).toUpperCase() + contact.getLastName().substring(0, 1);
        initialsTextView.setText(initials);


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
