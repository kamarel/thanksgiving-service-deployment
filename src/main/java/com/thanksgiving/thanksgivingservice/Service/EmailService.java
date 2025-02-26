package com.thanksgiving.thanksgivingservice.Service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


import java.util.List;

@AllArgsConstructor
@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void send(SimpleMailMessage email) {
        mailSender.send(email);
    }

    public void sendHarvestNotification(List<String> emails) {
        try {
            for (String email : emails) {
                SimpleMailMessage message = new SimpleMailMessage();
                message.setTo(email);
                message.setSubject("Thanks Giving Notification");
                message.setText("Dear Member, an exciting new Thanks Giving event has begun on");

                mailSender.send(message);
            }
        } catch (MailException e) {
            System.err.println("Error sending an email: " + e.getMessage());
        }
    }
}
