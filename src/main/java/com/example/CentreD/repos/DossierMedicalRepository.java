package com.example.CentreD.repos;

import com.example.CentreD.entities.DossierMedical;
import com.example.CentreD.entities.Patient;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DossierMedicalRepository extends JpaRepository<DossierMedical,String> {
    List<DossierMedical> findByMedecinTraitantId(Long id);
    DossierMedical findDossierMedicalByPatient_Id(Long id);
    DossierMedical findByNumeroDossierIs(String id);

}
