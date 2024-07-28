package com.example.tour_travel.service.impl;

import com.example.tour_travel.entity.User;
import com.example.tour_travel.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class EmailServiceImpl implements EmailService {

    @Value("${spring.mail.from}")
    private String from;

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private SpringTemplateEngine templateEngine;

    @Override
    public void sendVerificationEmail(User user, String token) {

        String subject = "Adventure - Verify your email address";
        String confirmationLink = "http://localhost:8080/verify?token=" +token;
        String recipient = user.getEmail();

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("username", user.getUsername());
        model.put("verificationUrl",confirmationLink);
        String body = "Hello " + user.getFirstName() + ",\n\n" +
                "Thank you for signing up for Tour Travel! Please verify your email address by clicking the link below:\n\n" +
                confirmationLink + "\n\n" +
                "If you did not sign up for Tour Travel, please ignore this email and let us know.\n\n" +
                "Best regards,\n";
        model.put("body",body);

        sendHtmlEmail(recipient,subject,"verify-email.html",model);
    }

    private void sendHtmlEmail(String to,String subject,String templateName,Map<String,Object> model){
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
            Context context = new Context();
            context.setVariables(model);
            context.setVariable("logoUrl","http://localhost:8080/images/log.png");
            String htmlContent = templateEngine.process(templateName,context);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlContent,true);

            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send email",e);
        }
    }
}




















