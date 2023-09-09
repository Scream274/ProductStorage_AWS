package com.example.storage.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import static com.example.storage.constants.MailConstants.*;

@Getter
@Setter
public class MailDto {
    @NotBlank(message = NULL_FIELD_ERROR_MSG)
    @Size(min = MIN_NAME_LENGTH, max = MAX_NAME_LENGTH, message = FIELD_NAME_ERROR_MSG)
    String firstName;

    @NotBlank(message = NULL_FIELD_ERROR_MSG)
    @Size(min = MIN_NAME_LENGTH, max = MAX_NAME_LENGTH, message = FIELD_NAME_ERROR_MSG)
    String lastName;

    @NotBlank(message = NULL_FIELD_ERROR_MSG)
    @Email(message = FIELD_EMAIL_ERROR_MSG)
    String email;

    @NotBlank(message = NULL_FIELD_ERROR_MSG)
    @Size(min = MIN_MSG_LENGTH, max = MAX_MSG_LENGTH, message = FIELD_MESSAGE_ERROR_MSG)
    String message;
}
