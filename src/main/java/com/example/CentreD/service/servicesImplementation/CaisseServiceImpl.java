package com.example.CentreD.service.servicesImplementation;

import com.example.CentreD.entities.Caisse;
import com.example.CentreD.repos.CaisseRepository;
import com.example.CentreD.service.servicesDeclaration.CaisseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CaisseServiceImpl implements CaisseService {

    @Autowired
    private CaisseRepository caisseRepository;

    @Override
    public List<Caisse> getAllCaisse() {
        return caisseRepository.findAll();
    }

    @Override
    public Optional<Caisse> getCaisseById(Long id) {
        return caisseRepository.findById(id);
    }

    @Override
    public Caisse addCaisse(Caisse caisse) {
        return caisseRepository.save(caisse);
    }

    @Override
    public Caisse updateCaisse(Caisse caisse) {
        // Ensure the caisse exists before updating
        if (caisseRepository.existsById(caisse.getIdCaisse())) {
            return caisseRepository.save(caisse);
        } else {
            // Handle the case where the caisse doesn't exist
            // or provide a different approach based on your requirements
            return null;
        }
    }

    @Override
    public void deleteCaisseById(Long id) {
        caisseRepository.deleteById(id);
    }
}
