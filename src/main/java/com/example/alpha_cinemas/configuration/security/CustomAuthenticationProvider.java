package com.example.alpha_cinemas.configuration.security;

import com.example.alpha_cinemas.configuration.security.jwt.CustomUserDetail;
import com.example.alpha_cinemas.configuration.security.jwt.CustomUserDetailService;
import com.example.alpha_cinemas.enums.ApiErrorCode;
import com.example.alpha_cinemas.exception.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
     private final CustomUserDetailService customUserDetailService;
     private final PasswordEncoder passwordEncoder;

    @Autowired
    @Lazy
    public CustomAuthenticationProvider(CustomUserDetailService customUserDetailService, PasswordEncoder passwordEncoder) {
        this.customUserDetailService = customUserDetailService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws ApiException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();

        CustomUserDetail userDetails = customUserDetailService.loadUserByUsername(email);

        if (userDetails == null) {
            throw new ApiException(ApiErrorCode.USER_NOT_FOUND);
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new ApiException(ApiErrorCode.PASSWORD_INCORRECT);
        }

        return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
