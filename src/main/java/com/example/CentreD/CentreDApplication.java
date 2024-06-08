package com.example.CentreD;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import com.example.CentreD.entities.Consultation;
import com.example.CentreD.entities.Dentiste;
import com.example.CentreD.entities.DossierMedical;
import com.example.CentreD.entities.Patient;
import com.example.CentreD.entities.SituationFinanciere;
import com.example.CentreD.entities.Utilisateur;
import com.example.CentreD.enums.TypeConsultation;
import com.example.CentreD.repos.ConsultationRepository;
import com.example.CentreD.repos.DentisteRepository;
import com.example.CentreD.repos.DossierMedicalRepository;
import com.example.CentreD.repos.PatientRepository;
import com.example.CentreD.repos.RoleRepository;
import com.example.CentreD.repos.SituationFinancierRepository;
import com.example.CentreD.repos.UtilisateurRepository;

import jakarta.transaction.Transactional;

@SpringBootApplication
public class CentreDApplication {

    public static void main(String[] args) {
        SpringApplication.run(CentreDApplication.class, args);
    }

    @Component
    public class DataLoader implements CommandLineRunner {

        @Autowired
        UtilisateurRepository utilisateurRepository;
        @Autowired
        PatientRepository patientRepository;
        @Autowired
        DentisteRepository dentisteRepository;
        @Autowired
        RoleRepository roleRepository;
        @Autowired
        DossierMedicalRepository dossierMedicalRepository;
        @Autowired
        ConsultationRepository consultationRepository;
        @Autowired
        SituationFinancierRepository situationFinancierRepository;

        @Override
        @Transactional
        public void run(String... args) throws Exception {



        
            
	
        }
    }
}


	
// }
