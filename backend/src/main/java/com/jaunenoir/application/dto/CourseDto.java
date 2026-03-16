package com.jaunenoir.application.dto;

import com.jaunenoir.domain.enums.ModePaiement;
import com.jaunenoir.domain.enums.StatutCourse;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record CourseDto(
        UUID id,
        UUID passagerId,
        String passagerNom,
        UUID chauffeurId,
        String chauffeurNom,
        Double departLatitude,
        Double departLongitude,
        Double arriveeLatitude,
        Double arriveeLongitude,
        StatutCourse statut,
        BigDecimal prix,
        ModePaiement modePaiement,
        LocalDateTime dateCreation
) {}
