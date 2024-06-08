package com.example.CentreD.service.servicesImplementation;

import com.example.CentreD.entities.Facture;
import com.example.CentreD.repos.FactureRepository;
import com.example.CentreD.service.servicesDeclaration.FactureService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FactureServiceImpl implements FactureService {

    @Autowired
    private FactureRepository factureRepository;

    @Override
    public Facture saveFacture(Facture facture) {
        return factureRepository.save(facture);
    }

    @Override
    public List<Facture> getAllFactures() {
        return factureRepository.findAll();
    }

    @Override
    public Optional<Facture> getFactureById(Long id) {
        return factureRepository.findById(id);
    }

    @Override
    public Facture updateFacture(Long id, Facture facture) {
        if (factureRepository.existsById(id)) {
            facture.setIdFacture(id);
            return factureRepository.save(facture);
        } else {
            throw new RuntimeException("Facture with id " + id + " not found");
        }
    }

    @Override
    public void deleteFacture(Long id) {
        if (factureRepository.existsById(id)) {
            factureRepository.deleteById(id);
        } else {
            throw new RuntimeException("Facture with id " + id + " not found");
        }
    }
}
