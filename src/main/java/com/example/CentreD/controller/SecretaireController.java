package com.example.CentreD.controller;

import com.example.CentreD.entities.Secretaire;
import com.example.CentreD.entities.Utilisateur;
import com.example.CentreD.enums.Assurance;
import com.example.CentreD.enums.StatusEmploye;
import com.example.CentreD.service.servicesDeclaration.SecretaireService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.transaction.Transactional;
import java.util.List;

@Controller
@SessionAttributes("user")
public class SecretaireController {

    @Autowired
    private SecretaireService secretaireService;

    @ModelAttribute("user")
    public Utilisateur getUser(Model model) {
        return (Utilisateur) model.getAttribute("user");
    }

    @GetMapping("/secretaires")
    public String getAllSecretaires(@ModelAttribute("user") Utilisateur user, Model model) {
        List<Secretaire> secretaires = secretaireService.getAllSecretaires();
        model.addAttribute("secretaires", secretaires);
        model.addAttribute("connectedUserName", user.getNom());
        return "secretaires";
    }

    @GetMapping("/secretaire/new")
    public String createSecretaireForm(@ModelAttribute("user") Utilisateur user, Model model) {
        Secretaire secretaire = new Secretaire();
        model.addAttribute("secretaire", secretaire);
        model.addAttribute("connectedUserName", user.getNom());
        model.addAttribute("assurances", Assurance.values());
        model.addAttribute("statusEmployes", StatusEmploye.values());
        return "create-secretaire";
    }

    @PostMapping("/secretaire/save")
    public String saveSecretaire(@ModelAttribute("user") Utilisateur user, @ModelAttribute("secretaire") Secretaire secretaire) {
        secretaireService.saveSecretaire(secretaire);
        return "redirect:/secretaires";
    }

    @GetMapping("/secretaire/edit/{id}")
    public String editSecretaireForm(@PathVariable("id") Long id, @ModelAttribute("user") Utilisateur user, Model model) {
        Secretaire secretaire = secretaireService.getSecretaireById(id);
        model.addAttribute("secretaire", secretaire);
        model.addAttribute("connectedUserName", user.getNom());
        return "edit-secretaire";
    }

    @PostMapping("/secretaire/update/{id}")
    public String updateSecretaire(@PathVariable("id") Long id, @ModelAttribute("user") Utilisateur user, @ModelAttribute("secretaire") Secretaire secretaire) {
        secretaireService.updateSecretaire(id, secretaire);
        return "redirect:/secretaires";
    }

    @GetMapping("/secretaire/delete/{id}")
    @Transactional
    public String deleteSecretaire(@PathVariable("id") Long id, @ModelAttribute("user") Utilisateur user, Model model) {
        secretaireService.deleteSecretaire(id);
        return "redirect:/secretaires";
    }
}
