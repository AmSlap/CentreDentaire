package com.example.CentreD.repos;

import com.example.CentreD.entities.Caisse;
import com.example.CentreD.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;



public interface CaisseRepository extends JpaRepository<Caisse, Long> {
    Optional<Caisse> findByIdCaisse(Long id);

}