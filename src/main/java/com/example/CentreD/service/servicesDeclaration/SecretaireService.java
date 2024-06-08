package com.example.CentreD.service.servicesDeclaration;

import java.util.List;
import com.example.CentreD.entities.Secretaire;

public interface SecretaireService {
    Secretaire saveSecretaire(Secretaire secretaire);
    Secretaire updateSecretaire(Long id, Secretaire secretaire);
    void deleteSecretaire(Long id);
    Secretaire getSecretaireById(Long id);
    List<Secretaire> getAllSecretaires();
    // Add more methods as needed
}
