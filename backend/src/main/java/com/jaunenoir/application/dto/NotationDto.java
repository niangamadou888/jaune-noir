package com.jaunenoir.application.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record NotationDto(
        UUID id,
        UUID courseId,
        UUID auteurId,
        String auteurNom,
        UUID evalueId,
        String evalueNom,
        Integer note,
        String commentaire,
        LocalDateTime dateCreation
) {}
