package com.example.alpha_cinemas.mapper;

import com.example.alpha_cinemas.dto.request.UserRequest;
import com.example.alpha_cinemas.dto.response.UserResponse;
import com.example.alpha_cinemas.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse toDto(User user);
    User toEntity(UserRequest request);
}
