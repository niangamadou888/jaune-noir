package com.jaunenoir.web.controller;

import com.jaunenoir.application.dto.CourseDto;
import com.jaunenoir.application.dto.request.CourseRequest;
import com.jaunenoir.domain.enums.StatutCourse;
import com.jaunenoir.service.CourseService;
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
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
@Tag(name = "Courses", description = "Gestion des courses")
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    @Operation(summary = "Créer une nouvelle course")
    public ResponseEntity<CourseDto> creer(@Valid @RequestBody CourseRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.creer(request));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtenir une course par son identifiant")
    public ResponseEntity<CourseDto> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(courseService.findById(id));
    }

    @GetMapping("/passager/{passagerId}")
    @Operation(summary = "Lister les courses d'un passager")
    public ResponseEntity<List<CourseDto>> findByPassager(@PathVariable UUID passagerId) {
        return ResponseEntity.ok(courseService.findByPassager(passagerId));
    }

    @GetMapping("/chauffeur/{chauffeurId}")
    @Operation(summary = "Lister les courses d'un chauffeur")
    public ResponseEntity<List<CourseDto>> findByChauffeur(@PathVariable UUID chauffeurId) {
        return ResponseEntity.ok(courseService.findByChauffeur(chauffeurId));
    }

    @GetMapping("/statut/{statut}")
    @Operation(summary = "Lister les courses par statut")
    public ResponseEntity<List<CourseDto>> findByStatut(@PathVariable StatutCourse statut) {
        return ResponseEntity.ok(courseService.findByStatut(statut));
    }

    @PatchMapping("/{courseId}/accepter")
    @Operation(summary = "Un chauffeur accepte une course")
    public ResponseEntity<CourseDto> accepter(@PathVariable UUID courseId,
                                               @RequestParam UUID chauffeurId) {
        return ResponseEntity.ok(courseService.accepter(courseId, chauffeurId));
    }

    @PatchMapping("/{courseId}/statut")
    @Operation(summary = "Mettre à jour le statut d'une course")
    public ResponseEntity<CourseDto> mettreAJourStatut(@PathVariable UUID courseId,
                                                        @RequestParam StatutCourse statut) {
        return ResponseEntity.ok(courseService.mettreAJourStatut(courseId, statut));
    }
}
