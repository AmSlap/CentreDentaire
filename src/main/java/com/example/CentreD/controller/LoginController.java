package com.example.CentreD.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.example.CentreD.entities.Patient;
import com.example.CentreD.entities.Utilisateur;
import com.example.CentreD.repos.PatientRepository;
import com.example.CentreD.service.servicesDeclaration.UtilisateurService;

@Controller
@SessionAttributes("user")
public class LoginController {

    @Autowired
    UtilisateurService utilisateurService;

    @Autowired
    PatientRepository patientRepository;

    @GetMapping("/")
    public String login() {
        return "login";
    }

    @PostMapping("/home")
    public String register(@RequestParam String email, @RequestParam String password, Model model) {
        Utilisateur utilisateur = utilisateurService.checkUtilisateur(email, password);
        if (utilisateur == null) {
            System.out.println("The user is null");
            return "login";
        }

        model.addAttribute("user", utilisateur);
        setModelAttributes(model, utilisateur);

        return "home";
    }
    @GetMapping("/home")
    public String home(Model model, @ModelAttribute("user") Utilisateur utilisateur) {
        if (utilisateur == null) {
            return "redirect:/";
        }
        setModelAttributes(model, utilisateur);
        return "home";
    }

    @ModelAttribute("user")
    public Utilisateur getUser(Model model) {
        return (Utilisateur) model.getAttribute("user");
    }

    private void setModelAttributes(Model model, Utilisateur utilisateur) {
        List<Patient> patients = patientRepository.findAll().stream()
                .filter(p -> p.getDossierMedical().getMedecinTraitant().getId() == utilisateur.getId())
                .toList();
        model.addAttribute("patients", patients);

        List<Patient> todayPatients = patients.stream()
                .filter(p -> !p.getDossierMedical().getConsultations().isEmpty())
                .filter(p -> p.getDossierMedical().getConsultations().get(0).getDateConsultation().equals(LocalDate.now()))
                .toList();
        model.addAttribute("todayPatients", todayPatients);
    }

    @GetMapping("/logout")
    public String logout(SessionStatus status) {
        status.setComplete();  // Invalidate the session
        return "redirect:/";
    }
}
