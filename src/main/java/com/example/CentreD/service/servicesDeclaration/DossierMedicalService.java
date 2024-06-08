package com.example.CentreD.services;

import com.example.CentreD.entities.DossierMedical;

import java.util.List;

public interface DossierMedicalService {
    DossierMedical findById(String id);
    List<DossierMedical> findAll();
    DossierMedical save(DossierMedical dossierMedical);
    void deleteById(String id);
}
