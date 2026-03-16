package com.jaunenoir.application.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record NotationRequest(
        @NotNull(message = "L'identifiant de la course est obligatoire")
        UUID courseId,

        @NotNull(message = "L'identifiant de l'auteur est obligatoire")
        UUID auteurId,

        @NotNull(message = "L'identifiant de l'évalué est obligatoire")
        UUID evalueId,

        @NotNull(message = "La note est obligatoire")
        @Min(value = 1, message = "La note minimale est 1")
        @Max(value = 5, message = "La note maximale est 5")
        Integer note,

        String commentaire
) {}
