package com.example.CentreD.service.servicesImplementation;

import java.util.List;
import java.util.Optional;

import com.example.CentreD.repos.SecretaireRepository;
import com.example.CentreD.service.servicesDeclaration.SecretaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.CentreD.entities.Secretaire;

@Service
@Transactional
public class SecretaireServiceImpl implements SecretaireService {

    @Autowired
    private SecretaireRepository secretaireRepository;

    @Override
    public Secretaire saveSecretaire(Secretaire secretaire) {
        return secretaireRepository.save(secretaire);
    }

    @Override
    public Secretaire updateSecretaire(Long id, Secretaire secretaire) {
        Optional<Secretaire> optionalSecretaire = secretaireRepository.findById(id);
        if (optionalSecretaire.isPresent()) {
            Secretaire existingSecretaire = optionalSecretaire.get();
            existingSecretaire.setSalaireDeBase(secretaire.getSalaireDeBase());
            existingSecretaire.setDateRetourConge(secretaire.getDateRetourConge());
            existingSecretaire.setAssurance(secretaire.getAssurance());
            existingSecretaire.setStatusActuel(secretaire.getStatusActuel());
            existingSecretaire.setPrime(secretaire.getPrime());
            // Update other fields as necessary
            return secretaireRepository.save(existingSecretaire);
        } else {
            // Handle the case where the Secretaire does not exist
            // You might want to throw an exception or return null
            return null;
        }
    }

    @Override
    public void deleteSecretaire(Long id) {
        secretaireRepository.deleteById(id);
    }

    @Override
    public Secretaire getSecretaireById(Long id) {
        return secretaireRepository.findById(id).orElse(null);
    }

    @Override
    public List<Secretaire> getAllSecretaires() {
        return secretaireRepository.findAll();
    }

    // Add more methods as needed
}
