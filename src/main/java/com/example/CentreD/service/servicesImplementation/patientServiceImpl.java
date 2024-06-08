package com.example.CentreD.service.servicesImplementation;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CentreD.entities.Consultation;
import com.example.CentreD.entities.Dentiste;
import com.example.CentreD.entities.DossierMedical;
import com.example.CentreD.entities.Patient;
import com.example.CentreD.entities.SituationFinanciere;
import com.example.CentreD.entities.Utilisateur;
import com.example.CentreD.enums.GroupeSanguin;
import com.example.CentreD.enums.TypeConsultation;
import com.example.CentreD.repos.ConsultationRepository;
import com.example.CentreD.repos.DossierMedicalRepository;
import com.example.CentreD.repos.PatientRepository;
import com.example.CentreD.repos.SituationFinancierRepository;
import com.example.CentreD.service.servicesDeclaration.PatientService;

@Service
public class patientServiceImpl implements PatientService {


    @Autowired
    PatientRepository patientRepository;
    @Autowired
    SituationFinancierRepository situationFinancierRepository;
    @Autowired 
    DossierMedicalRepository dossierMedicalRepository;
    @Autowired
    ConsultationRepository consultationRepository;


    @Override
    public List<Patient> getAllPatient() {
        return patientRepository.findAll();
    }

    @Override
    public List<Patient> getDentistPatients(Long dentistId) {
        return getAllPatient().stream().filter(p -> p.getDossierMedical().getMedecinTraitant().getId() == dentistId).toList();
    }

    @Override
    public List<Patient> getFilteredTodayPatients(Long dentistId) {
        return getDentistPatients(dentistId).stream().filter(p -> p.getDossierMedical().getConsultations().get(0).getDateConsultation().equals(LocalDate.now())).toList();
    }

    @Override
    public Patient addPatient(Utilisateur user, String nom, String prenom, String dateNaissance, String sexe,
            String mutuelle, String groupeSanguin, String profession) {

             // this is the  defalut    

            SituationFinanciere situationFinanciere = new SituationFinanciere();   
            situationFinanciere.setDateCreation(LocalDate.now()); 
            situationFinanciere =  situationFinancierRepository.save(situationFinanciere);

            DossierMedical dossierMedical = new DossierMedical();
            dossierMedical.setNumeroDossier(UUID.randomUUID().toString());
            dossierMedical.setDateCreation(LocalDate.now());
            dossierMedical.setMedecinTraitant((Dentiste) user);
            dossierMedical.setSituationFinanciere(situationFinanciere);
            dossierMedical = dossierMedicalRepository.save(dossierMedical);

            Consultation consultation = new Consultation();
            consultation.setDateConsultation(LocalDate.now());    
            consultation.setTypeConsultation(TypeConsultation.CONSULTATION_GENERALE);    
            consultation.setDossierMedical(dossierMedical);
            consultationRepository.save(consultation);
                
            Patient patient = new Patient();
            patient.setDossierMedical(dossierMedical);
            patient.setNom(nom);
            patient.setPrenom(prenom);
            patient.setSexe(sexe);
            String[] parses = dateNaissance.split("-");
            patient.setDateNaissance( LocalDate.of(Integer.parseInt(parses[0]), Integer.parseInt(parses[1]), Integer.parseInt(parses[2])));
            patient.setGroupeSanguin(GroupeSanguin.valueOf(groupeSanguin));
            patient.setProfession(profession);    

            
            return patientRepository.save(patient);

    }
    @Override
    public Optional<Patient> getPatientById(Long id) {
        return patientRepository.findById(id);
    }

 


    
}
