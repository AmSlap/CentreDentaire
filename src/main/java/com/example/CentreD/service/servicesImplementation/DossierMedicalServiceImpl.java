package com.example.CentreD.services.impl;

import com.example.CentreD.entities.DossierMedical;
import com.example.CentreD.repos.DossierMedicalRepository;
import com.example.CentreD.repos.DossierMedicalRepository;
import com.example.CentreD.services.DossierMedicalService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DossierMedicalServiceImpl implements DossierMedicalService {

    private final DossierMedicalRepository dossierMedicalRepository;

    public DossierMedicalServiceImpl(DossierMedicalRepository dossierMedicalRepository) {
        this.dossierMedicalRepository = dossierMedicalRepository;
    }

    @Override
    public DossierMedical findById(String id) {
        Optional<DossierMedical> optionalDossierMedical = dossierMedicalRepository.findById(id);
        return optionalDossierMedical.orElse(null);
    }

    @Override
    public List<DossierMedical> findAll() {
        return dossierMedicalRepository.findAll();
    }

    @Override
    public DossierMedical save(DossierMedical dossierMedical) {
        return dossierMedicalRepository.save(dossierMedical);
    }

    @Override
    public void deleteById(String id) {
        dossierMedicalRepository.deleteById(id);
    }
}
