package com.example.alpha_cinemas.service.impl;

import com.example.alpha_cinemas.dto.response.UserResponse;
import com.example.alpha_cinemas.mapper.UserMapper;
import com.example.alpha_cinemas.model.User;
import com.example.alpha_cinemas.repository.UserRepository;
import com.example.alpha_cinemas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserResponse> handleGetAllUsers(){
        List<User> users = userRepository.findAll();

        return users.stream().map(userMapper::toDto).collect(Collectors.toList());
    }
}
