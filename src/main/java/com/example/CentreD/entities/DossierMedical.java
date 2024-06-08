package com.example.CentreD.entities;

import java.time.LocalDate;
import java.util.List;

import com.example.CentreD.enums.StatutPaiement;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DossierMedical {

    @Id
    private String numeroDossier;
    private LocalDate dateCreation;
    @Enumerated(EnumType.STRING)
    private StatutPaiement statutPaiement;
    // @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "dossierMedical")
    private List<Consultation> consultations;
    @OneToOne(mappedBy = "dossierMedical")
    private Patient patient;
    @ManyToOne
    private Dentiste medecinTraitant;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private SituationFinanciere situationFinanciere;
    
}
