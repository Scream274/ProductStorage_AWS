package com.example.storage.service;

public interface MailService {

    void sendEmail(String to, String message, String subject);

    String buildMsgForUser(String firstName, String lastName);

    String buildMsgForAdmin(String firstName, String lastName, String email, String message);
}
