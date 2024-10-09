package com.example.alpha_cinemas.configuration;

import com.example.alpha_cinemas.enums.ERole;
import com.example.alpha_cinemas.model.Role;
import com.example.alpha_cinemas.model.User;
import com.example.alpha_cinemas.repository.RoleRepository;
import com.example.alpha_cinemas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
public class ApplicationInitConfig {

    private final PasswordEncoder passwordEncoder;
    @Autowired
    public ApplicationInitConfig(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }
    @Bean
    ApplicationRunner applicationRunner(RoleRepository roleRepository, UserRepository userRepository){
        return args -> {
            if(roleRepository.findRoleByRoleName(ERole.ADMIN) == null){
                Role role = Role.builder().roleName(ERole.ADMIN).build();
                roleRepository.save(role);
            }

            if(roleRepository.findRoleByRoleName(ERole.USER) == null){
                Role role = Role.builder().roleName(ERole.USER).build();
                roleRepository.save(role);
            }

            if (userRepository.findUserByUserName("admin") == null){
                Role adminRole =roleRepository.findRoleByRoleName(ERole.ADMIN);
                User user = User.builder()
                        .email("ngohoang1310@gmail.com")
                        .userName("admin")
                        .password(passwordEncoder.encode("admin123"))
                        .roles(Set.of(adminRole))
                        .build();

                userRepository.save(user);
            }

        };
    }

}
