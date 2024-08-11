package com.charging.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class RegisterRequest {
    @NotBlank(message = "username can not be blank")
    private String username;
    @NotBlank(message = "E-mail can not be empty")
    @Email(message = "E-mail format is incorrect")
    private String email;
    @NotBlank(message = "password can not be blank")
    private String password;
}
