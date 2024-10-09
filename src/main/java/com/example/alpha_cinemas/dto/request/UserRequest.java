package com.example.alpha_cinemas.dto.request;

import com.example.alpha_cinemas.model.Role;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {
    @NotNull(message = "You must enter your email !")
    @Email(message = "Email is invalid !")
    private String email;
    @Size(min = 6, message = "Your password must have at least 6 characters !")
    private String password;
    @NotNull(message = "You must enter your username !")
    private String userName;
    @Size(min = 10,max = 10, message = "Your phone must have at least 10 digits !")
    private String phone;
    private String avatar;
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dob;
    @NotNull
    private String gender;
    private Set<String> rolesString = new HashSet<>();

    private Set<Role> roles = new HashSet<>();
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
