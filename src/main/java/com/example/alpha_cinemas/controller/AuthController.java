package com.example.alpha_cinemas.controller;

import com.example.alpha_cinemas.configuration.security.jwt.CustomUserDetail;
import com.example.alpha_cinemas.dto.request.LoginRequest;
import com.example.alpha_cinemas.dto.request.UserRequest;
import com.example.alpha_cinemas.dto.response.ApiResponse;
import com.example.alpha_cinemas.dto.response.JwtResponse;
import com.example.alpha_cinemas.dto.response.UserResponse;
import com.example.alpha_cinemas.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = "/auth/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserRequest payload) {
        UserResponse response = authService.handleRegister(payload);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>(HttpStatus.CREATED.value(), "Register successful", response, null));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest payload) {
        JwtResponse response = authService.handleLogin(payload);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.OK.value(), "Login successful !", response, null));
    }

    @GetMapping("/auth/me")
    public ResponseEntity<?> getMe(@AuthenticationPrincipal CustomUserDetail userDetail) {
        Long userId = userDetail.getUser().getId();
        UserResponse response = authService.handleGetMe(userId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.OK.value(), "Ok !", response, null));
    }

    @PostMapping("/auth/refresh-token")
    public ResponseEntity<?> refreshToken(@AuthenticationPrincipal CustomUserDetail userDetail){
        Long userId = userDetail.getUser().getId();
        JwtResponse response = authService.handeRefreshToken(String.valueOf(userId));
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.OK.value(), "Ok !", response, null));
    }

    @DeleteMapping("/auth/users/{id}/log-out")
    public ResponseEntity<?> logOut(@PathVariable String id){
        authService.handleLogOut(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.OK.value(), "Log out successfully !", null, null));
    }
}
