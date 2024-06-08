package com.example.CentreD.service.servicesDeclaration;

import com.example.CentreD.entities.Facture;

import java.util.List;
import java.util.Optional;

public interface FactureService {
    Facture saveFacture(Facture facture);
    List<Facture> getAllFactures();
    Optional<Facture> getFactureById(Long id);
    Facture updateFacture(Long id, Facture facture);
    void deleteFacture(Long id);
}
