package com.example.alpha_cinemas.service;

import com.example.alpha_cinemas.dto.request.LoginRequest;
import com.example.alpha_cinemas.dto.request.UserRequest;
import com.example.alpha_cinemas.dto.response.ApiResponse;
import com.example.alpha_cinemas.dto.response.JwtResponse;
import com.example.alpha_cinemas.dto.response.UserResponse;
import com.example.alpha_cinemas.model.User;

public interface AuthService {
    UserResponse handleRegister(UserRequest payload);
    JwtResponse handleLogin(LoginRequest payload);
    JwtResponse handeRefreshToken(String userId);
    UserResponse handleGetMe(Long id);
    void handleLogOut(String userId);
}
