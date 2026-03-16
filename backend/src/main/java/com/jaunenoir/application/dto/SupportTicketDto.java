package com.jaunenoir.application.dto;

import com.jaunenoir.domain.enums.StatutTicket;

import java.time.LocalDateTime;
import java.util.UUID;

public record SupportTicketDto(
        UUID id,
        UUID utilisateurId,
        String utilisateurNom,
        String sujet,
        String description,
        StatutTicket statut,
        LocalDateTime dateCreation,
        LocalDateTime dateMiseAJour
) {}
