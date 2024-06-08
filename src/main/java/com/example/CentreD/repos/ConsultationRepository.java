package com.example.CentreD.repos;

import com.example.CentreD.entities.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
    List<Consultation> findByDossierMedicalPatientId(Long patientId);
}
