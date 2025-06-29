
package com.ecom.EmailServiceForUser;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendUserCredentials(String toEmail, String username, String password) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("🎉 Welcome to E-Commerce Platform - Your Account Details");
        message.setSentDate(new Date());
        message.setText(
            "Hi " + username + ",\n\n"
            + "Your account has been created successfully!\n\n"
            + "🆔 Username: " + username + "\n"
            + "🔐 Password: " + password + "\n\n"
            + "You can now login and start exploring our platform.\n\n"
            + "Regards,\n"
            + "E-Commerce Team"
        );

        // Optional: Uncomment if needed
        // message.setFrom("bablugiri1947@gmail.com");

        mailSender.send(message);
    }
}
