package com.jaunenoir.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record VehiculeRequest(
        @NotNull(message = "L'identifiant du chauffeur est obligatoire")
        UUID chauffeurId,

        @NotBlank(message = "La marque est obligatoire")
        String marque,

        @NotBlank(message = "Le modèle est obligatoire")
        String modele,

        @NotBlank(message = "L'immatriculation est obligatoire")
        String immatriculation,

        String couleur,

        Integer annee
) {}
