package com.example.CentreD.repos;

import com.example.CentreD.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,String> {
    Role findByNomRole(String nomRole);
}
