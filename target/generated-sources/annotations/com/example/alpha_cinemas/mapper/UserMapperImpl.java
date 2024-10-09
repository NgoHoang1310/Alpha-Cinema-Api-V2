package com.example.alpha_cinemas.mapper;

import com.example.alpha_cinemas.dto.request.UserRequest;
import com.example.alpha_cinemas.dto.response.UserResponse;
import com.example.alpha_cinemas.enums.Gender;
import com.example.alpha_cinemas.model.Role;
import com.example.alpha_cinemas.model.User;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-10T00:20:35+0700",
    comments = "version: 1.6.0, compiler: javac, environment: Java 22.0.2 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserResponse toDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponse userResponse = new UserResponse();

        userResponse.setId( user.getId() );
        userResponse.setEmail( user.getEmail() );
        userResponse.setUserName( user.getUserName() );
        userResponse.setPhone( user.getPhone() );
        userResponse.setAvatar( user.getAvatar() );
        if ( user.getGender() != null ) {
            userResponse.setGender( user.getGender().name() );
        }
        userResponse.setDob( user.getDob() );
        Set<Role> set = user.getRoles();
        if ( set != null ) {
            userResponse.setRoles( new LinkedHashSet<Role>( set ) );
        }
        userResponse.setCreatedAt( user.getCreatedAt() );
        userResponse.setUpdatedAt( user.getUpdatedAt() );

        return userResponse;
    }

    @Override
    public User toEntity(UserRequest request) {
        if ( request == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.email( request.getEmail() );
        user.password( request.getPassword() );
        user.userName( request.getUserName() );
        user.phone( request.getPhone() );
        if ( request.getGender() != null ) {
            user.gender( Enum.valueOf( Gender.class, request.getGender() ) );
        }
        user.dob( request.getDob() );
        user.avatar( request.getAvatar() );
        Set<Role> set = request.getRoles();
        if ( set != null ) {
            user.roles( new LinkedHashSet<Role>( set ) );
        }
        user.createdAt( request.getCreatedAt() );
        user.updatedAt( request.getUpdatedAt() );

        return user.build();
    }
}
