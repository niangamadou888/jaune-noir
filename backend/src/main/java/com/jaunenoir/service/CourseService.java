package com.jaunenoir.service;

import com.jaunenoir.application.dto.CourseDto;
import com.jaunenoir.application.dto.request.CourseRequest;
import com.jaunenoir.domain.enums.StatutCourse;

import java.util.List;
import java.util.UUID;

public interface CourseService {
    CourseDto creer(CourseRequest request);
    CourseDto findById(UUID id);
    List<CourseDto> findByPassager(UUID passagerId);
    List<CourseDto> findByChauffeur(UUID chauffeurId);
    List<CourseDto> findByStatut(StatutCourse statut);
    CourseDto accepter(UUID courseId, UUID chauffeurId);
    CourseDto mettreAJourStatut(UUID courseId, StatutCourse statut);
}
