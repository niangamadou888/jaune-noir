package com.jaunenoir.service.impl;

import com.jaunenoir.application.dto.CourseDto;
import com.jaunenoir.application.dto.request.CourseRequest;
import com.jaunenoir.domain.entity.Course;
import com.jaunenoir.domain.entity.Utilisateur;
import com.jaunenoir.domain.enums.StatutCourse;
import com.jaunenoir.domain.exception.BusinessException;
import com.jaunenoir.infrastructure.repository.CourseRepository;
import com.jaunenoir.infrastructure.repository.UtilisateurRepository;
import com.jaunenoir.service.CourseService;
import com.jaunenoir.shared.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final UtilisateurRepository utilisateurRepository;

    @Override
    @Transactional
    public CourseDto creer(CourseRequest request) {
        Utilisateur passager = utilisateurRepository.findById(request.passagerId())
                .orElseThrow(() -> new BusinessException(Constants.UTILISATEUR_NON_TROUVE));

        Course course = Course.builder()
                .passager(passager)
                .departLatitude(request.departLatitude())
                .departLongitude(request.departLongitude())
                .arriveeLatitude(request.arriveeLatitude())
                .arriveeLongitude(request.arriveeLongitude())
                .modePaiement(request.modePaiement())
                .build();

        return toDto(courseRepository.save(course));
    }

    @Override
    public CourseDto findById(UUID id) {
        return toDto(getOrThrow(id));
    }

    @Override
    public List<CourseDto> findByPassager(UUID passagerId) {
        Utilisateur passager = utilisateurRepository.findById(passagerId)
                .orElseThrow(() -> new BusinessException(Constants.UTILISATEUR_NON_TROUVE));
        return courseRepository.findByPassagerOrderByDateCreationDesc(passager)
                .stream().map(this::toDto).toList();
    }

    @Override
    public List<CourseDto> findByChauffeur(UUID chauffeurId) {
        Utilisateur chauffeur = utilisateurRepository.findById(chauffeurId)
                .orElseThrow(() -> new BusinessException(Constants.UTILISATEUR_NON_TROUVE));
        return courseRepository.findByChauffeurOrderByDateCreationDesc(chauffeur)
                .stream().map(this::toDto).toList();
    }

    @Override
    public List<CourseDto> findByStatut(StatutCourse statut) {
        return courseRepository.findByStatut(statut).stream().map(this::toDto).toList();
    }

    @Override
    @Transactional
    public CourseDto accepter(UUID courseId, UUID chauffeurId) {
        Course course = getOrThrow(courseId);
        if (course.getStatut() != StatutCourse.EN_ATTENTE) {
            throw new BusinessException("La course n'est plus disponible");
        }
        Utilisateur chauffeur = utilisateurRepository.findById(chauffeurId)
                .orElseThrow(() -> new BusinessException(Constants.UTILISATEUR_NON_TROUVE));
        course.setChauffeur(chauffeur);
        course.setStatut(StatutCourse.ACCEPTEE);
        return toDto(courseRepository.save(course));
    }

    @Override
    @Transactional
    public CourseDto mettreAJourStatut(UUID courseId, StatutCourse statut) {
        Course course = getOrThrow(courseId);
        course.setStatut(statut);
        return toDto(courseRepository.save(course));
    }

    private Course getOrThrow(UUID id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new BusinessException(Constants.COURSE_NON_TROUVEE));
    }

    private CourseDto toDto(Course c) {
        return new CourseDto(
                c.getId(),
                c.getPassager().getId(),
                c.getPassager().getPrenom() + " " + c.getPassager().getNom(),
                c.getChauffeur() != null ? c.getChauffeur().getId() : null,
                c.getChauffeur() != null ? c.getChauffeur().getPrenom() + " " + c.getChauffeur().getNom() : null,
                c.getDepartLatitude(), c.getDepartLongitude(),
                c.getArriveeLatitude(), c.getArriveeLongitude(),
                c.getStatut(), c.getPrix(), c.getModePaiement(), c.getDateCreation()
        );
    }
}
