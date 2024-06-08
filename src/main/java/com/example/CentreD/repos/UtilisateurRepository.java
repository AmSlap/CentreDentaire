package com.example.CentreD.repos;

import com.example.CentreD.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface UtilisateurRepository extends JpaRepository<Utilisateur,Long> {
    Utilisateur findUtilisateurByNom(String nom);
    Utilisateur findByEmail(String email);
}