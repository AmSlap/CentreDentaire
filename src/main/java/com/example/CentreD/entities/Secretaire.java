package com.example.CentreD.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

// Assuming there's an enum Assurance and StatusEmploye defined in your project
import com.example.CentreD.enums.Assurance;
import com.example.CentreD.enums.StatusEmploye;

// Assuming Utilisateur is another entity that Secrétaire extends
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Secretaire extends Utilisateur {

    private Double salaireDeBase;
    private LocalDate dateRetourConge; // Changed "Congé" to "Conge" to avoid special character issues

    @Enumerated(EnumType.STRING)
    private Assurance assurance;

    @Enumerated(EnumType.STRING)
    private StatusEmploye statusActuel;

    private Double prime;
}
