package com.example.CentreD.repos;

import com.example.CentreD.entities.Secretaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecretaireRepository extends JpaRepository<Secretaire, Long> {
    // You can add custom query methods if needed
}
