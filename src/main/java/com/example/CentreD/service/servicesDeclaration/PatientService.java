package com.example.CentreD.service.servicesDeclaration;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.CentreD.entities.Patient;
import com.example.CentreD.entities.Utilisateur;



public interface PatientService {

    List<Patient> getAllPatient();
    List<Patient> getDentistPatients(Long dentistId);
    List<Patient> getFilteredTodayPatients(Long dentistId);
    Patient   addPatient(Utilisateur user,String nom, String prenom, String dateNaissance,
    String sexe,
    String mutuelle,
    String groupeSanguin,
    String profession
    );
    Optional<Patient> getPatientById(Long id);
    
}
