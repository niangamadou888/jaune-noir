package com.jaunenoir.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record SupportTicketRequest(
        @NotNull(message = "L'identifiant de l'utilisateur est obligatoire")
        UUID utilisateurId,

        @NotBlank(message = "Le sujet est obligatoire")
        String sujet,

        @NotBlank(message = "La description est obligatoire")
        String description
) {}
