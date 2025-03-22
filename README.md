🔐 Firebase Authentication - Login & Signup

📌 Overview

This project is an Android app that implements Login and Signup authentication using Firebase Authentication. Users can register with email & password and log in securely. The app follows MVVM architecture and ensures smooth authentication using Firebase services.

🚀 Features

🔹 User Signup with Email & Password

🔹 Secure User Login

🔹 Firebase Authentication Integration

🔹 Password Validation & Error Handling

🔹 Logout Functionality

🛠 Tech Stack

Language: Kotlin

UI Framework: XML / Jetpack Compose

Architecture: MVVM (Model-View-ViewModel)

Database: Firebase Firestore (Optional for user data)

Authentication: Firebase Auth

🔧 Setup & Installation

Clone the Repository

git clone https://github.com/your-username/firebase-auth-app.git

Open in Android Studio

Setup Firebase Project

Go to Firebase Console

Create a new project and enable Firebase Authentication.

Download google-services.json and place it in app/ directory.

Sync Gradle & Run the App

gradle sync

🔥 Firebase Configuration

Add the Firebase dependency in build.gradle (Module: app):

implementation 'com.google.firebase:firebase-auth:21.0.1'

Initialize Firebase Auth in MainActivity.kt:

private lateinit var auth: FirebaseAuth
auth = FirebaseAuth.getInstance()

🔥 Future Enhancements

Google & Facebook Sign-In Integration

Password Reset & Email Verification

Profile Management

🤝 Contribution

Contributions are welcome! Feel free to open issues and submit pull requests.


🚀 Start building secure authentication with Firebase today!

