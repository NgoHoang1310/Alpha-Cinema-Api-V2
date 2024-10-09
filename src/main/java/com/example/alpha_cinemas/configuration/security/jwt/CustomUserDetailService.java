package com.example.alpha_cinemas.configuration.security.jwt;

import com.example.alpha_cinemas.enums.ApiErrorCode;
import com.example.alpha_cinemas.exception.ApiException;
import com.example.alpha_cinemas.model.User;
import com.example.alpha_cinemas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public CustomUserDetail loadUserByUsername(String email) throws ApiException {
        User user = userRepository.findUserByEmail(email);
        if (user == null) {
           throw new ApiException(ApiErrorCode.USER_NOT_FOUND);
        }
        return new CustomUserDetail(user);
    }

    public CustomUserDetail loadUserById(Long userId) throws ApiException{
        User user = userRepository.findUserById(userId);
        if (user == null) {
            return null;
        }
        return new CustomUserDetail(user);
    }
}
