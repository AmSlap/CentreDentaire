package com.example.CentreD.service.servicesImplementation;

import com.example.CentreD.entities.Consultation;
import com.example.CentreD.repos.ConsultationRepository;
import com.example.CentreD.service.servicesDeclaration.ConsultationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultationServiceImpl implements ConsultationService {

    @Autowired
    private ConsultationRepository consultationRepository;

    @Override
    public List<Consultation> getAllConsultations() {
        return consultationRepository.findAll();
    }

    @Override
    public Optional<Consultation> getConsultationById(Long id) {
        return consultationRepository.findById(id);
    }

    @Override
    public void saveConsultation(Consultation consultation) {
        consultationRepository.save(consultation);
    }

    @Override
    public void deleteConsultation(Long id) {
        consultationRepository.deleteById(id);
    }

    @Override
    public List<Consultation> getConsultationsByPatientId(Long patientId) {
        return consultationRepository.findByDossierMedicalPatientId(patientId);
    }
}
