package com.example.alpha_cinemas.repository;

import com.example.alpha_cinemas.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findUserByEmail(String email);
    User findUserByUserName(String userName);
    User findUserById(Long id);
}
