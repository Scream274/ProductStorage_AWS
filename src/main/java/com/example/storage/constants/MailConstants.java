package com.example.storage.constants;

public final class MailConstants {
    private MailConstants() {
    }

    public static final String ADMIN_EMAIL = "admin_email@gmail.com";
    public static final String LABEL = "Product-storage.ua";
    public static final String EMAIL_SUBJECT = "Лист зворотного зв'язку";
    public static final String FAIL_SEND_MSG = "failed to send email";
    public static final String ADMIN_TEMPLATE_PATH = "templates/msg_for_admin.html";
    public static final String USER_TEMPLATE_PATH = "templates/msg_for_user.html";
    public static final int MIN_NAME_LENGTH = 2;
    public static final int MAX_NAME_LENGTH = 30;
    public static final int MIN_MSG_LENGTH = 10;
    public static final int MAX_MSG_LENGTH = 300;
    public static final String FIELD_NAME_ERROR_MSG =
            "Name should be between " + MIN_NAME_LENGTH + " and " + MAX_NAME_LENGTH + " characters";
    public static final String FIELD_EMAIL_ERROR_MSG = "Invalid email";
    public static final String FIELD_MESSAGE_ERROR_MSG =
            "Message should be between " + MIN_MSG_LENGTH + " and " + MAX_MSG_LENGTH + " characters";
    public static final String NULL_FIELD_ERROR_MSG = "Filed cant be empty";
}
