package com.jaunenoir.application.dto;

import com.jaunenoir.domain.enums.RoleUtilisateur;

import java.time.LocalDateTime;
import java.util.UUID;

public record UtilisateurDto(
        UUID id,
        String nom,
        String prenom,
        String telephone,
        String email,
        RoleUtilisateur role,
        boolean actif,
        LocalDateTime dateCreation
) {}
