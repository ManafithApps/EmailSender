# EmailSender Android Library 📧

A modern, fluent API for sending emails in Android using the JavaMail API. This library simplifies the process of sending emails with HTML support and attachments, handling the background threading automatically.

[![JitPack](https://jitpack.io/v/ManafithApps/EmailSender.svg)](https://jitpack.io/#ManafithApps/EmailSender)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

## Features
* ✅ **Easy Configuration:** Set up your credentials in seconds.
* ✅ **HTML Support:** Send rich-text emails with HTML tags.
* ✅ **Attachments:** Send files easily using Android URIs.
* ✅ **Callback Listeners:** Get notified when the email is sent successfully or if an error occurs.
* ✅ **Background Execution:** No need to worry about `NetworkOnMainThreadException`.

---

## Installation

### 1. Add JitPack to your project
In your `settings.gradle` file, add the JitPack repository:

```gradle
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven { url '[https://jitpack.io](https://jitpack.io)' }
    }
}
```

2. Add the dependency
Add the following to your build.gradle (Module: app):

Gradle
```
dependencies {
    implementation 'com.github.ManafithApps:EmailSender:1.0.3'
}
```

Usage
1. Simple Email (Text only)
Java
```
EmailConfig config = new EmailConfig.Builder()
        .setCredentials("your-email@gmail.com", "your-app-password")
        .build();

EmailSender.with(this)
        .setConfigure(config)
        .to("recipient@example.com")
        .subject("Test Email")
        .message("Hello! This is a simple test email.")
        .send(new OnSendEmailListener() {
            @Override
            public void onSuccessful() {
                Toast.makeText(context, "Email Sent!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(String error) {
                Log.e("EmailError", error);
            }
        });
```

2. HTML Email with Attachment
Java
```
EmailSender.with(this)
        .setConfigure(config)
        .to("client@example.com")
        .subject("Invoice #123")
        .message("<h1>Thank you!</h1><p>Your invoice is attached.</p>", true) // true for HTML
        .addAttachment(fileUri) // Pass the URI of your file
        .send(null);
```
Important: Gmail App Password 🔐
For Gmail users, you cannot use your regular account password. You must generate an App Password:

Enable 2-Step Verification in your Google Account.

Search for App Passwords in your security settings.

Select 'Mail' and 'Other (Custom Name)'.

Use the 16-character code generated as your password in setCredentials.

Requirements
Minimum SDK: 21

Internet Permission: <uses-permission android:name="android.permission.INTERNET" />

License
Copyright 2025 ManafithApps Licensed under the Apache License, Version 2.0.
