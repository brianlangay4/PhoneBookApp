# Phone Book App Documentation

![phoneBookApp](https://github.com/brianlangay4/PhoneBookApp/assets/67788456/b3ded7ce-eee2-4213-bf32-23c54325a70d)

##
#BRIAN BARNABAS LANGAY
2322190027
##

## preview

![phoneBookApp_sc](https://github.com/brianlangay4/PhoneBookApp/assets/67788456/58382bb6-1222-49ed-b5b9-7796a4c22a93)


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

- Integration with cloud services for data synchronization.
- Customizable contact icons.
- Search functionality to quickly find contacts.
