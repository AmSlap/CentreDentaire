package com.example.CentreD.repos;

import com.example.CentreD.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;


public interface PatientRepository extends JpaRepository<Patient,Long> {
    Optional<Patient> findById(Long id);

}
