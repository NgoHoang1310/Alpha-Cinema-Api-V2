package com.example.alpha_cinemas.service.impl;

import com.example.alpha_cinemas.configuration.security.jwt.CustomUserDetail;
import com.example.alpha_cinemas.configuration.security.jwt.JwtUtil;
import com.example.alpha_cinemas.dto.request.LoginRequest;
import com.example.alpha_cinemas.dto.request.UserRequest;
import com.example.alpha_cinemas.dto.response.JwtResponse;
import com.example.alpha_cinemas.dto.response.UserResponse;
import com.example.alpha_cinemas.enums.ERole;
import com.example.alpha_cinemas.enums.ApiErrorCode;
import com.example.alpha_cinemas.exception.ApiException;
import com.example.alpha_cinemas.mapper.UserMapper;
import com.example.alpha_cinemas.model.Role;
import com.example.alpha_cinemas.model.User;
import com.example.alpha_cinemas.repository.RoleRepository;
import com.example.alpha_cinemas.repository.UserRepository;
import com.example.alpha_cinemas.service.AuthService;
import com.example.alpha_cinemas.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {
    @Value("${jwt.access.expiration}")
    private long accessTokenExpiration;
    @Value("${jwt.refresh.expiration}")
    private long refreshTokenExpiration;
    private String refreshToken;

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final RedisService redisService;

    @Autowired
    public AuthServiceImpl(
            UserRepository userRepository,
            RoleRepository roleRepository,
            UserMapper userMapper,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            JwtUtil jwtUtil,
            RedisService redisService
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.redisService = redisService;
    }

    @Override
    public UserResponse handleRegister(UserRequest payload) {
        Set<String> strRoles = payload.getRolesString();
        Set<Role> roles = new HashSet<>();
        User user = userRepository.findUserByEmail(payload.getEmail());

        if (user != null) throw new ApiException(ApiErrorCode.USER_EXISTED);
        payload.setPassword(passwordEncoder.encode(payload.getPassword()));

        if (strRoles.isEmpty()) {
            Role userRole = roleRepository.findRoleByRoleName(ERole.USER);
            if (userRole == null) throw new RuntimeException("Error: Role is not found.");
            roles.add(userRole);
        }else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "ADMIN":
                        Role adminRole = roleRepository.findRoleByRoleName(ERole.ADMIN);
                        roles.add(adminRole);

                        break;
                    default:
                        Role userRole = roleRepository.findRoleByRoleName(ERole.USER);
                        roles.add(userRole);
                }
            });
        }
        payload.setRoles(roles);
        user = userRepository.save(userMapper.toEntity(payload));
        return userMapper.toDto(user);
    }

    @Override
    public JwtResponse handleLogin(LoginRequest payload) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(payload.getEmail(), payload.getPassword()));

        Long userId = ((CustomUserDetail) authentication.getPrincipal()).getUser().getId();
        String accessToken = jwtUtil.generateToken(userId, accessTokenExpiration);
        refreshToken = jwtUtil.generateToken(userId, refreshTokenExpiration);
        redisService.save(refreshToken,String.valueOf(userId));
        return new JwtResponse(accessToken, refreshToken);
    }

    @Override
    public JwtResponse handeRefreshToken(String userId){
        Object token = redisService.findById(userId);
        if (token == null) throw new ApiException(ApiErrorCode.INVALID_JWT_TOKEN);
        String accessToken = jwtUtil.generateToken(Long.parseLong(userId),accessTokenExpiration);
        return new JwtResponse(accessToken,refreshToken);
    }

    @Override
    public UserResponse handleGetMe(Long userId) {
        System.out.println(userId);
        User user = userRepository.findUserById(userId);
        return userMapper.toDto(user);
    }

    @Override
    public void handleLogOut(String userId){
        redisService.deleteById(userId);
    }

}
