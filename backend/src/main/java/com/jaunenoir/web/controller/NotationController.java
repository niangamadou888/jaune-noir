package com.jaunenoir.web.controller;

import com.jaunenoir.application.dto.NotationDto;
import com.jaunenoir.application.dto.request.NotationRequest;
import com.jaunenoir.service.NotationService;
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
@RequestMapping("/api/v1/notations")
@RequiredArgsConstructor
@Tag(name = "Notations", description = "Gestion des évaluations")
public class NotationController {

    private final NotationService notationService;

    @PostMapping
    @Operation(summary = "Soumettre une notation")
    public ResponseEntity<NotationDto> noter(@Valid @RequestBody NotationRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(notationService.noter(request));
    }

    @GetMapping("/course/{courseId}")
    @Operation(summary = "Obtenir les notations d'une course")
    public ResponseEntity<List<NotationDto>> findByCourse(@PathVariable UUID courseId) {
        return ResponseEntity.ok(notationService.findByCourse(courseId));
    }

    @GetMapping("/utilisateur/{utilisateurId}")
    @Operation(summary = "Obtenir les notations reçues par un utilisateur")
    public ResponseEntity<List<NotationDto>> findByEvalue(@PathVariable UUID utilisateurId) {
        return ResponseEntity.ok(notationService.findByEvalue(utilisateurId));
    }

    @GetMapping("/utilisateur/{utilisateurId}/moyenne")
    @Operation(summary = "Obtenir la note moyenne d'un utilisateur")
    public ResponseEntity<Double> getMoyenne(@PathVariable UUID utilisateurId) {
        return ResponseEntity.ok(notationService.getMoyenne(utilisateurId));
    }
}
