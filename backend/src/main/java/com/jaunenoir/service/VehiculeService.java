package com.jaunenoir.service;

import com.jaunenoir.application.dto.VehiculeDto;
import com.jaunenoir.application.dto.request.VehiculeRequest;

import java.util.List;
import java.util.UUID;

public interface VehiculeService {
    VehiculeDto enregistrer(VehiculeRequest request);
    VehiculeDto findById(UUID id);
    List<VehiculeDto> findByChauffeur(UUID chauffeurId);
    VehiculeDto activerDesactiver(UUID id, boolean actif);
    void supprimer(UUID id);
}
