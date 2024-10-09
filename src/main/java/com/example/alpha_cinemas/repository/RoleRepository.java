package com.example.alpha_cinemas.repository;

import com.example.alpha_cinemas.enums.ERole;
import com.example.alpha_cinemas.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findRoleByRoleName(ERole roleName);

}
