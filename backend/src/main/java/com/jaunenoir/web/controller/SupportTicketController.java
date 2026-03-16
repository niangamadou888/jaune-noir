package com.jaunenoir.web.controller;

import com.jaunenoir.application.dto.SupportTicketDto;
import com.jaunenoir.application.dto.request.SupportTicketRequest;
import com.jaunenoir.domain.enums.StatutTicket;
import com.jaunenoir.service.SupportTicketService;
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
@RequestMapping("/api/v1/support")
@RequiredArgsConstructor
@Tag(name = "Support", description = "Gestion des tickets de support")
public class SupportTicketController {

    private final SupportTicketService supportTicketService;

    @PostMapping
    @Operation(summary = "Créer un ticket de support")
    public ResponseEntity<SupportTicketDto> creer(@Valid @RequestBody SupportTicketRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(supportTicketService.creer(request));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtenir un ticket par son identifiant")
    public ResponseEntity<SupportTicketDto> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(supportTicketService.findById(id));
    }

    @GetMapping("/utilisateur/{utilisateurId}")
    @Operation(summary = "Lister les tickets d'un utilisateur")
    public ResponseEntity<List<SupportTicketDto>> findByUtilisateur(@PathVariable UUID utilisateurId) {
        return ResponseEntity.ok(supportTicketService.findByUtilisateur(utilisateurId));
    }

    @GetMapping("/statut/{statut}")
    @Operation(summary = "Lister les tickets par statut")
    public ResponseEntity<List<SupportTicketDto>> findByStatut(@PathVariable StatutTicket statut) {
        return ResponseEntity.ok(supportTicketService.findByStatut(statut));
    }

    @PatchMapping("/{id}/statut")
    @Operation(summary = "Mettre à jour le statut d'un ticket")
    public ResponseEntity<SupportTicketDto> mettreAJourStatut(@PathVariable UUID id,
                                                               @RequestParam StatutTicket statut) {
        return ResponseEntity.ok(supportTicketService.mettreAJourStatut(id, statut));
    }
}
