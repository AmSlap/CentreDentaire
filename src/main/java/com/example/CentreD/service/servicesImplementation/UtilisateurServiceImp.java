package com.example.CentreD.service.servicesImplementation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CentreD.entities.Personne;
import com.example.CentreD.entities.Role;
import com.example.CentreD.entities.Utilisateur;
import com.example.CentreD.repos.UtilisateurRepository;
import com.example.CentreD.service.servicesDeclaration.UtilisateurService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UtilisateurServiceImp implements UtilisateurService {

    @Autowired
    UtilisateurRepository utilisateurRepository;
    @Autowired 
    PersonneServiceImp personneServiceImp;


    @Override
    public Utilisateur checkUtilisateur(String email, String passowrd) {

            System.out.println(email);
            Utilisateur utilisateur = utilisateurRepository.findByEmail(email);

            if (utilisateur == null)
            {
                System.out.println("this email doenst exist");
                return null;
            }

            System.out.println("this is the inserted password :  " + passowrd);
            System.out.println("this is the user password  : " + utilisateur.getMotDePass());
            if (utilisateur != null && utilisateur.getMotDePass().equals(passowrd) == true)
                return utilisateur;
            
            System.out.println("the passwor doesnt match");  
            return null;
    }
    
    
    
}
