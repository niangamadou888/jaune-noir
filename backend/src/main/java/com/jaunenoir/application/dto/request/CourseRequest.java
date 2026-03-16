package com.jaunenoir.application.dto.request;

import com.jaunenoir.domain.enums.ModePaiement;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CourseRequest(
        @NotNull(message = "L'identifiant du passager est obligatoire")
        UUID passagerId,

        @NotNull(message = "La latitude de départ est obligatoire")
        Double departLatitude,

        @NotNull(message = "La longitude de départ est obligatoire")
        Double departLongitude,

        @NotNull(message = "La latitude d'arrivée est obligatoire")
        Double arriveeLatitude,

        @NotNull(message = "La longitude d'arrivée est obligatoire")
        Double arriveeLongitude,

        @NotNull(message = "Le mode de paiement est obligatoire")
        ModePaiement modePaiement
) {}
