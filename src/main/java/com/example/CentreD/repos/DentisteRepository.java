package com.example.CentreD.repos;

import com.example.CentreD.entities.Dentiste;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DentisteRepository extends JpaRepository<Dentiste,Long> {

    // List<Dentiste> findAll();
    // List<Dentiste> findByEmail(String email);;
    // Dentiste findByNom(String nom);
    // Optional<Dentiste> findById(Long id);

}
