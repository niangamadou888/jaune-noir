package com.jaunenoir.application.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record VehiculeDto(
        UUID id,
        UUID chauffeurId,
        String chauffeurNom,
        String marque,
        String modele,
        String immatriculation,
        String couleur,
        Integer annee,
        boolean actif,
        LocalDateTime dateCreation
) {}
