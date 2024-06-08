package com.example.CentreD.controller;

import com.example.CentreD.entities.*;
import com.example.CentreD.enums.GroupeSanguin;
import com.example.CentreD.enums.TypePaiement;
import com.example.CentreD.repos.ConsultationRepository;
import com.example.CentreD.repos.DossierMedicalRepository;
import com.example.CentreD.repos.PatientRepository;
import com.example.CentreD.repos.PersonneRepository;
import com.example.CentreD.repos.SituationFinancierRepository;
import com.example.CentreD.repos.UtilisateurRepository;
import com.example.CentreD.service.servicesDeclaration.CaisseService;
import com.example.CentreD.service.servicesDeclaration.ConsultationService;
import com.example.CentreD.service.servicesDeclaration.FactureService;
import com.example.CentreD.service.servicesDeclaration.PatientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Controller
@SessionAttributes("user")
public class FrontController {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private CaisseService caisseService;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private FactureService factureService;

    @Autowired
    private DossierMedicalRepository dossierMedicalRepository;

    @Autowired
    private SituationFinancierRepository situationFinancierRepository;

    @Autowired
    private PatientService patientService;

    @Autowired
    private ConsultationRepository consultationRepository;

    @Autowired
    private ConsultationService consultationService;

    @Autowired
    private PersonneRepository personneRepository;

    @ModelAttribute("user")
    public Utilisateur getUser(Model model) {
        return (Utilisateur) model.getAttribute("user");
    }

    @GetMapping("/profile")
    public String profile(@ModelAttribute("user") Utilisateur user, Model model) {
        model.addAttribute("user", user);
        return "profile";
    }

    @GetMapping("/modiferProfile")
    public String modifierProfile(@ModelAttribute("user") Utilisateur user, Model model) {
        model.addAttribute("user", user);
        return "modifierProfile";
    }

    @GetMapping("/patients")
    public String listPatients(@ModelAttribute("user") Utilisateur user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("patients", patientService.getDentistPatients(user.getId()));
        return "patients";
    }

    @GetMapping("/ajouterPatient")
    public String ajouterPatient(@ModelAttribute("user") Utilisateur user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("patients", patientService.getDentistPatients(user.getId()));
        return "ajouterPatient";
    }

    @PostMapping("/patients")
    public String patientForm(@ModelAttribute("user") Utilisateur user, @RequestParam("nom") String nom,
                              @RequestParam("prenom") String prenom,
                              @RequestParam("dateNaissance") String dateNaissance,
                              @RequestParam("sexe") String sexe,
                              @RequestParam("mutuelle") String mutuelle,
                              @RequestParam("groupeSanguin") String groupeSanguin,
                              @RequestParam("profession") String profession,
                              Model model) {

        patientService.addPatient(user, nom, prenom, dateNaissance, sexe, mutuelle, groupeSanguin, profession);

        model.addAttribute("patients", patientService.getDentistPatients(user.getId()));
        model.addAttribute("user", user);

        System.out.println(nom);
        return "patients";
    }

    @GetMapping("/caisse")
    public String caisse(@ModelAttribute("user") Utilisateur user, Model model) {
        model.addAttribute("user", user);
        return "caisse";
    }

    @PostMapping("/enregisterProfile")
    @Transactional
    public String redirectToProfile(@ModelAttribute("user") Utilisateur user, Model model) {
        utilisateurRepository.save(user);
        model.addAttribute("user", user);
        return "profile";
    }

    @GetMapping("/dossierMD/{id}")
    @Transactional
    public String dossierMedical(@ModelAttribute("user") Utilisateur user, @PathVariable("id") Long patientId, Model model) {
        Utilisateur oldUser = utilisateurRepository.findByEmail(user.getEmail());
        model.addAttribute("user", oldUser);
        model.addAttribute("id", patientId);
        List<DossierMedical> listDossierMedical = dossierMedicalRepository.findByMedecinTraitantId(oldUser.getId());
        DossierMedical dossierMedical2 = listDossierMedical.stream().filter(d -> d.getPatient().getId().equals(patientId)).findFirst().orElse(null);
        model.addAttribute("dossier", dossierMedical2);

        return "dossierMD";
    }

    @GetMapping("/situationFinanciere")
    public String situation() {
        return "situationFinanciere";
    }

    @GetMapping("/deletePatient/{id}")
    public String deletePatient(@ModelAttribute("user") Utilisateur user, @PathVariable("id") Long id, Model model) {
        Utilisateur oldUser = utilisateurRepository.findByEmail(user.getEmail());
        System.out.println(id);

        Patient patient = patientService.getDentistPatients(oldUser.getId()).stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
        if (patient == null) {
            // handle patient not found scenario
            return "patients";
        }

        Personne personne = personneRepository.findById(patient.getId()).orElse(null);
        DossierMedical dossierMedical = dossierMedicalRepository.findById(patient.getDossierMedical().getNumeroDossier()).orElse(null);
        List<Consultation> consultations = consultationRepository.findAll().stream().filter(c -> c.getDossierMedical().getNumeroDossier().equals(dossierMedical.getNumeroDossier())).toList();
        SituationFinanciere situationFinanciere = situationFinancierRepository.findById(dossierMedical.getSituationFinanciere().getIdSituationFinanciere()).orElse(null);

        consultationRepository.deleteAll(consultations);
        dossierMedicalRepository.delete(dossierMedical);
        situationFinancierRepository.delete(situationFinanciere);
        patientRepository.delete(patient);
        personneRepository.delete(personne);

        model.addAttribute("user", oldUser);
        model.addAttribute("patients", patientService.getDentistPatients(oldUser.getId()));

        return "patients";
    }

    @GetMapping("/caisse/{id}")
    public String viewCaisseDetails(@ModelAttribute("user") Utilisateur user, @PathVariable("id") Long id, Model model) {
        Optional<Caisse> caisseOptional = caisseService.getCaisseById(id);
        if (caisseOptional.isPresent()) {
            model.addAttribute("caisse", caisseOptional.get());
            model.addAttribute("user", user);
            return "caisse-details"; // Thymeleaf template name
        } else {
            return "caisse-not-found"; // Thymeleaf template for not found
        }
    }

    // Consultation-related methods
    @GetMapping("/consultations/{patientId}")
    public String showConsultations(@PathVariable("patientId") Long patientId, Model model) {
        Patient patient = patientRepository.findById(patientId).orElse(null);

        if (patient == null) {
            // Handle patient not found scenario
            return "error"; // or any appropriate error page
        }

        model.addAttribute("patient", patient);
        model.addAttribute("consultations", patient.getDossierMedical().getConsultations());
        return "consultation"; // Thymeleaf template name
    }




    @GetMapping("/consultation/delete/{id}")
    public String deleteConsultation(@PathVariable("id") Long id) {
        consultationService.deleteConsultation(id);
        return "redirect:/consultations"; // Redirect to the list of consultations
    }
    @GetMapping("/consultation/new")
    public String showConsultationForm(@RequestParam("patientId") Long patientId, Model model) {
        // Retrieve the patient by ID
        Patient patient = patientRepository.findById(patientId).orElse(null);
        Consultation consultation = new Consultation();
        // Set any default values or initialize properties if needed
        model.addAttribute("consultation", consultation);

        if (patient!=null) {
            // If the patient exists, retrieve the dossier medical

            DossierMedical dossierMedical = patient.getDossierMedical();

            // Add the dossier medical to the model
            model.addAttribute("dossierMedical", dossierMedical);
            model.addAttribute("patient",patient);
            // Add any other necessary attributes to the model

            return "new-consultation";
        } else {
            // Handle the case where the patient is not found
            return "error";
        }
    }

    @PostMapping("/consultation/save")
    public String saveConsultation(@ModelAttribute("consultation") Consultation consultation, @ModelAttribute("patient") Patient patient, Model model) {
        DossierMedical dossierMedical = dossierMedicalRepository.findDossierMedicalByPatient_Id(patient.getId());

        if (dossierMedical == null) {
            // Handle dossier medical not found scenario
            return "error"; // or any appropriate error page
        }

        consultation.setDossierMedical(dossierMedical);
        consultationRepository.save(consultation);
        return "redirect:/consultations/" + patient.getId();
    }

    @PostMapping("/consultation/new")
    public String addConsultation(@ModelAttribute("consultation") Consultation consultation, @ModelAttribute("patient") Patient patient) {
        // Retrieve the Patient based on the patientId

        if (patient!=null) {


            // Retrieve the DossierMedical associated with the Patient
            DossierMedical dossierMedical = patient.getDossierMedical();
            if (dossierMedical != null) {
                // Associate the consultation with the DossierMedical
                consultation.setDossierMedical(dossierMedical);

                // Save the consultation
                consultationRepository.save(consultation);

                return "redirect:/consultations"; // Redirect to wherever you want after adding the consultation
            } else {
                // Handle case where DossierMedical is not found for the Patient
                return "redirect:/error"; // Redirect to an error page
            }
        } else {
            // Handle case where Patient with given ID is not found
            return "redirect:/error"; // Redirect to an error page
        }
    }



    // Method to show the factures page
    @GetMapping("/factures")
    public String listFactures(Model model) {
        List<Facture> factures = factureService.getAllFactures();
        model.addAttribute("factures", factures);
        return "factures";
    }

    // Method to show the new facture form
    @GetMapping("/facture/new")
    public String newFactureForm(Model model) {
        model.addAttribute("facture", new Facture());
        model.addAttribute("typePaiements", TypePaiement.values());
        return "facture-form";
    }

    // Method to edit an existing facture
    @GetMapping("/facture/edit/{id}")
    public String editFactureForm(@PathVariable("id") Long id, Model model) {
        Facture facture = factureService.getFactureById(id).orElse(new Facture());
        model.addAttribute("facture", facture);
        return "facture-form";
    }

    // Method to save the facture
    @PostMapping("/facture/save")
    public String saveFacture(@ModelAttribute("facture") Facture facture) {
        factureService.saveFacture(facture);
        return "redirect:/factures";
    }

    // Method to delete a facture
    @GetMapping("/facture/delete/{id}")
    public String deleteFacture(@PathVariable("id") Long id) {
        factureService.deleteFacture(id);
        return "redirect:/factures";
    }
}
