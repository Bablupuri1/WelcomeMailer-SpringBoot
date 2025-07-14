# WelcomeMailer-SpringBoot
# ✉️ Spring Boot User Registration System with Email Notification

This is a backend-only Spring Boot application for **user registration** with smart features like email notifications and auto-generated credentials.

---

##  Features

- ✅ User registration with email and phone number
- 🔒 Prevents duplicate registration (by email or phone)
- 🔐 Generates random **username** and **password** automatically
- 📩 Sends an email with credentials upon successful registration
- ⚠️ Sends a warning email if someone tries to register again using an existing email/phone — includes existing credentials

---

## 📧 Example Email Flows

### 📤 On New Registration:
Welcome to Our Service!
Your account has been successfully created.

Username: user123xyz
Password: @xYz123#!


---

##  Technologies Used

| Layer | Technology |
|-------|------------|
| Backend | Java, Spring Boot |
| Email | Spring Mail (JavaMailSender) |
| Build Tool | Maven |
| Database | MySQL |
| IDE | IntelliJ / Eclipse |

---


---

## 🔄 Future Enhancements

- ✅ Add frontend using React or HTML/CSS
- 🔐 Encrypt passwords (using BCrypt)
- 🔄 Password reset via email link
- 📊 Admin dashboard to manage users

---

## ⚙️ Setup Instructions

> Note: Since it's backend-only, use **Postman** or **cURL** to test the API.

1. Clone the repo
2. Configure `application.properties` with your email SMTP settings and MySQL DB
3. Run the application
4. Use Postman to send POST request to `/register`

---

##  Sample API (POST `/register`)

```json
{
  "fullName": "Bablu Kumar",
  "email": "bablu@example.com",
  "phone": "7633017940"
}


