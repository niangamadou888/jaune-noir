package com.jaunenoir.application.dto.request;

import com.jaunenoir.domain.enums.RoleUtilisateur;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record InscriptionRequest(
        @NotBlank(message = "Le nom est obligatoire")
        String nom,

        @NotBlank(message = "Le prénom est obligatoire")
        String prenom,

        @NotBlank(message = "Le téléphone est obligatoire")
        @Pattern(regexp = "^(\\+221|00221)?[7][0-9]{8}$", message = "Numéro de téléphone sénégalais invalide")
        String telephone,

        @Email(message = "Email invalide")
        String email,

        @NotNull(message = "Le rôle est obligatoire")
        RoleUtilisateur role,

        @NotBlank(message = "Le mot de passe est obligatoire")
        String motDePasse
) {}
