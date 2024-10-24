package com.example.alpha_cinemas.service;

import com.example.alpha_cinemas.configuration.security.jwt.CustomUserDetail;
import com.example.alpha_cinemas.dto.request.LoginRequest;
import com.example.alpha_cinemas.dto.request.UserRequest;
import com.example.alpha_cinemas.dto.response.JwtResponse;
import com.example.alpha_cinemas.dto.response.UserResponse;

import java.util.List;
import java.util.Set;

public interface UserService {

    List<UserResponse> handleGetAllUsers();
}
