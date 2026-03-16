package com.jaunenoir.service.impl;

import com.jaunenoir.application.dto.VehiculeDto;
import com.jaunenoir.application.dto.request.VehiculeRequest;
import com.jaunenoir.domain.entity.Utilisateur;
import com.jaunenoir.domain.entity.Vehicule;
import com.jaunenoir.domain.exception.BusinessException;
import com.jaunenoir.infrastructure.repository.UtilisateurRepository;
import com.jaunenoir.infrastructure.repository.VehiculeRepository;
import com.jaunenoir.service.VehiculeService;
import com.jaunenoir.shared.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VehiculeServiceImpl implements VehiculeService {

    private final VehiculeRepository vehiculeRepository;
    private final UtilisateurRepository utilisateurRepository;

    @Override
    @Transactional
    public VehiculeDto enregistrer(VehiculeRequest request) {
        if (vehiculeRepository.existsByImmatriculation(request.immatriculation())) {
            throw new BusinessException(Constants.IMMATRICULATION_DEJA_UTILISEE);
        }
        Utilisateur chauffeur = utilisateurRepository.findById(request.chauffeurId())
                .orElseThrow(() -> new BusinessException(Constants.UTILISATEUR_NON_TROUVE));

        Vehicule vehicule = Vehicule.builder()
                .chauffeur(chauffeur)
                .marque(request.marque())
                .modele(request.modele())
                .immatriculation(request.immatriculation())
                .couleur(request.couleur())
                .annee(request.annee())
                .build();

        return toDto(vehiculeRepository.save(vehicule));
    }

    @Override
    public VehiculeDto findById(UUID id) {
        return toDto(getOrThrow(id));
    }

    @Override
    public List<VehiculeDto> findByChauffeur(UUID chauffeurId) {
        Utilisateur chauffeur = utilisateurRepository.findById(chauffeurId)
                .orElseThrow(() -> new BusinessException(Constants.UTILISATEUR_NON_TROUVE));
        return vehiculeRepository.findByChauffeur(chauffeur).stream().map(this::toDto).toList();
    }

    @Override
    @Transactional
    public VehiculeDto activerDesactiver(UUID id, boolean actif) {
        Vehicule vehicule = getOrThrow(id);
        vehicule.setActif(actif);
        return toDto(vehiculeRepository.save(vehicule));
    }

    @Override
    @Transactional
    public void supprimer(UUID id) {
        getOrThrow(id);
        vehiculeRepository.deleteById(id);
    }

    private Vehicule getOrThrow(UUID id) {
        return vehiculeRepository.findById(id)
                .orElseThrow(() -> new BusinessException(Constants.VEHICULE_NON_TROUVE));
    }

    private VehiculeDto toDto(Vehicule v) {
        return new VehiculeDto(
                v.getId(),
                v.getChauffeur().getId(),
                v.getChauffeur().getPrenom() + " " + v.getChauffeur().getNom(),
                v.getMarque(), v.getModele(), v.getImmatriculation(),
                v.getCouleur(), v.getAnnee(), v.isActif(), v.getDateCreation()
        );
    }
}
