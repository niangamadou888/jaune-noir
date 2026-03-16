package com.jaunenoir.web.controller;

import com.jaunenoir.application.dto.VehiculeDto;
import com.jaunenoir.application.dto.request.VehiculeRequest;
import com.jaunenoir.service.VehiculeService;
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
@RequestMapping("/api/v1/vehicules")
@RequiredArgsConstructor
@Tag(name = "Véhicules", description = "Gestion des véhicules")
public class VehiculeController {

    private final VehiculeService vehiculeService;

    @PostMapping
    @Operation(summary = "Enregistrer un nouveau véhicule")
    public ResponseEntity<VehiculeDto> enregistrer(@Valid @RequestBody VehiculeRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vehiculeService.enregistrer(request));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtenir un véhicule par son identifiant")
    public ResponseEntity<VehiculeDto> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(vehiculeService.findById(id));
    }

    @GetMapping("/chauffeur/{chauffeurId}")
    @Operation(summary = "Lister les véhicules d'un chauffeur")
    public ResponseEntity<List<VehiculeDto>> findByChauffeur(@PathVariable UUID chauffeurId) {
        return ResponseEntity.ok(vehiculeService.findByChauffeur(chauffeurId));
    }

    @PatchMapping("/{id}/statut")
    @Operation(summary = "Activer ou désactiver un véhicule")
    public ResponseEntity<VehiculeDto> activerDesactiver(@PathVariable UUID id,
                                                          @RequestParam boolean actif) {
        return ResponseEntity.ok(vehiculeService.activerDesactiver(id, actif));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un véhicule")
    public ResponseEntity<Void> supprimer(@PathVariable UUID id) {
        vehiculeService.supprimer(id);
        return ResponseEntity.noContent().build();
    }
}
