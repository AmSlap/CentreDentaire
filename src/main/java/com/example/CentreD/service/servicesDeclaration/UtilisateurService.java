package com.example.CentreD.service.servicesDeclaration;

import java.util.List;

import com.example.CentreD.entities.Role;
import com.example.CentreD.entities.Utilisateur;

public interface UtilisateurService {

    Utilisateur checkUtilisateur(String email,String passowrd);
    
}
