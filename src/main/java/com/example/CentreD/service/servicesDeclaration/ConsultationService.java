package com.example.CentreD.service.servicesDeclaration;

import com.example.CentreD.entities.Consultation;
import java.util.List;
import java.util.Optional;

public interface ConsultationService {
    List<Consultation> getAllConsultations();
    Optional<Consultation> getConsultationById(Long id);
    void saveConsultation(Consultation consultation);
    void deleteConsultation(Long id);
    List<Consultation> getConsultationsByPatientId(Long patientId); // New method
}

