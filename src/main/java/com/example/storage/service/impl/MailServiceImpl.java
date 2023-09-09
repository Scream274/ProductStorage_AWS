package com.example.storage.service.impl;

import com.example.storage.service.MailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import static com.example.storage.constants.MailConstants.*;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    private final JavaMailSender mailSender;

    @Override
    public void sendEmail(final String to, final String message, final String subject) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(message, true);
            helper.setTo(to);
            helper.setFrom(ADMIN_EMAIL, LABEL);
            helper.setSubject(subject);
            mailSender.send(mimeMessage);
        } catch (MessagingException | UnsupportedEncodingException ex) {
            throw new IllegalArgumentException(FAIL_SEND_MSG);
        }
    }

    @Override
    public String buildMsgForUser(final String firstName, final String lastName) {
        String emailTemplate = readHtmlTemplateFromFile(USER_TEMPLATE_PATH);

        emailTemplate = emailTemplate.replace("{{firstName}}", firstName);
        emailTemplate = emailTemplate.replace("{{lastName}}", lastName);

        return emailTemplate;
    }

    @Override
    public String buildMsgForAdmin(final String firstName, final String lastName,
                                   final String email, final String message) {
        String emailTemplate = readHtmlTemplateFromFile(ADMIN_TEMPLATE_PATH);

        emailTemplate = emailTemplate.replace("{{firstName}}", firstName);
        emailTemplate = emailTemplate.replace("{{lastName}}", lastName);
        emailTemplate = emailTemplate.replace("{{email}}", email);
        emailTemplate = emailTemplate.replace("{{message}}", message);

        return emailTemplate;
    }

    private String readHtmlTemplateFromFile(final String filePath) {
        ClassPathResource resource = new ClassPathResource(filePath);

        byte[] byteArray;
        try {
            byteArray = FileCopyUtils.copyToByteArray(resource.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new String(byteArray, StandardCharsets.UTF_8);
    }
}
