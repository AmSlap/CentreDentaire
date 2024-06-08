package com.example.CentreD.service.servicesDeclaration;

import org.springframework.stereotype.Service;

import com.example.CentreD.entities.Personne;
import com.example.CentreD.repos.PersonneRepository;


public interface PersonneService {

    Personne findPersonneByEmail(String email);
    void deletePersonneById(Long id);
    Personne findPersonneById(Long id);

    
}
