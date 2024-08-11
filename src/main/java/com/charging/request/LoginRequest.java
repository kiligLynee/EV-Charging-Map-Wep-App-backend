package com.charging.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class LoginRequest {
    @NotBlank(message = "The mailbox cannot be empty")
    @Email(message = "E-mail format is incorrect")
    private String email;
    @NotBlank(message = "password can not be blank")
    private String password;
}
