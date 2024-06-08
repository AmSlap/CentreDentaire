package com.example.CentreD.service.servicesDeclaration;

import com.example.CentreD.entities.Caisse;
import com.example.CentreD.entities.Patient;

import java.util.List;
import java.util.Optional;

public interface CaisseService {
    List<Caisse> getAllCaisse();
    Optional<Caisse> getCaisseById(Long id);
    Caisse addCaisse(Caisse caisse);
    Caisse updateCaisse(Caisse caisse);
    void deleteCaisseById(Long id);
}
