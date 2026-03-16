package com.jaunenoir.web.controller;

import com.jaunenoir.application.dto.UtilisateurDto;
import com.jaunenoir.application.dto.request.InscriptionRequest;
import com.jaunenoir.service.UtilisateurService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/utilisateurs")
@RequiredArgsConstructor
@Tag(name = "Utilisateurs", description = "Gestion des utilisateurs")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    @PostMapping("/inscription")
    @Operation(summary = "Inscrire un nouvel utilisateur")
    public ResponseEntity<UtilisateurDto> inscrire(@Valid @RequestBody InscriptionRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(utilisateurService.inscrire(request));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtenir un utilisateur par son identifiant")
    public ResponseEntity<UtilisateurDto> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(utilisateurService.findById(id));
    }

    @GetMapping
    @Operation(summary = "Lister tous les utilisateurs")
    public ResponseEntity<List<UtilisateurDto>> findAll() {
        return ResponseEntity.ok(utilisateurService.findAll());
    }

    @PatchMapping("/{id}/statut")
    @Operation(summary = "Activer ou désactiver un utilisateur")
    public ResponseEntity<UtilisateurDto> activerDesactiver(@PathVariable UUID id,
                                                             @RequestParam boolean actif) {
        return ResponseEntity.ok(utilisateurService.activerDesactiver(id, actif));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un utilisateur")
    public ResponseEntity<Void> supprimer(@PathVariable UUID id) {
        utilisateurService.supprimer(id);
        return ResponseEntity.noContent().build();
    }
}
