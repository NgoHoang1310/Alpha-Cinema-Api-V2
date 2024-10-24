package com.example.alpha_cinemas.configuration.security;

import com.example.alpha_cinemas.configuration.security.jwt.AccessDeniedJwtHandler;
import com.example.alpha_cinemas.configuration.security.jwt.AuthEntryPointJwt;
import com.example.alpha_cinemas.configuration.security.jwt.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final CustomAuthenticationProvider customAuthenticationProvider;

    private final AuthEntryPointJwt unauthorizedHandler;
    private final AccessDeniedJwtHandler accessDeniedJwtHandler;

    @Autowired
    @Lazy
    public SecurityConfig(
            CustomAuthenticationProvider customAuthenticationProvider,
            AuthEntryPointJwt authEntryPointJwt,
            AccessDeniedJwtHandler accessDeniedJwtHandler
    ) {
        this.customAuthenticationProvider = customAuthenticationProvider;
        this.unauthorizedHandler = authEntryPointJwt;
        this.accessDeniedJwtHandler = accessDeniedJwtHandler;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .exceptionHandling(
                        exception -> exception
                                .authenticationEntryPoint(unauthorizedHandler)
                                .accessDeniedHandler(accessDeniedJwtHandler))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        //auth
                        .requestMatchers("/auth/login").permitAll()
                        .requestMatchers("/auth/register").permitAll()
                        .requestMatchers("/auth/users/{id}/log-out").permitAll()
                        //category
                        .requestMatchers("/categories").permitAll()
                        .requestMatchers("/categories/{id}").permitAll()

                        //user
                        .requestMatchers("/users").permitAll()

                        //movie
                        .requestMatchers("/movies").permitAll()
                        .requestMatchers("/movies/{id}").permitAll()

                        //movie detail
                        .requestMatchers("/movie-detail/{id}").permitAll()

                        //theater
                        .requestMatchers("/theaters").permitAll()
                        .requestMatchers("/theaters/{id}").permitAll()

                        //room
                        .requestMatchers("/rooms").permitAll()
                        .requestMatchers("/rooms/type").permitAll()

                        //seat
                        .requestMatchers("/seats").permitAll()
                        .requestMatchers("/seats/type").permitAll()
                        .requestMatchers("/seats/room/{id}").permitAll()

                        //schedule
                        .requestMatchers("/schedules").permitAll()
                        .requestMatchers("/schedules/days").permitAll()
                        .requestMatchers("/schedules/{id}").permitAll()



                        .anyRequest().authenticated()
                )
                .authenticationProvider(customAuthenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)

        ;
        return http.build();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public JwtFilter jwtAuthenticationFilter() {
        return new JwtFilter();
    }


}
