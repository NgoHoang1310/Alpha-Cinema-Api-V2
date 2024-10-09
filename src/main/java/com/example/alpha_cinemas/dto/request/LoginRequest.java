package com.example.alpha_cinemas.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    @NotBlank(message = "Email must be not blank !")
    @Email(message = "Email is invalid !")
    private String email;

    @NotBlank(message = "Password must be not blank !")
    @Size(min = 6, message = "Your password must have at least 6 characters !")
    private String password;
}
