package com.example.alpha_cinemas.controller;

import com.example.alpha_cinemas.configuration.security.jwt.CustomUserDetail;
import com.example.alpha_cinemas.dto.response.ApiResponse;
import com.example.alpha_cinemas.dto.response.UserResponse;
import com.example.alpha_cinemas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<ApiResponse<List<UserResponse>>> getAllUsers() {
        List<UserResponse> responses = userService.handleGetAllUsers();
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.OK.value(), "Ok !", responses, null));
    }

}
