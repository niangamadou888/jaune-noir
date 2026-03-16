package com.jaunenoir.service.impl;

import com.jaunenoir.application.dto.NotationDto;
import com.jaunenoir.application.dto.request.NotationRequest;
import com.jaunenoir.domain.entity.Course;
import com.jaunenoir.domain.entity.Notation;
import com.jaunenoir.domain.entity.Utilisateur;
import com.jaunenoir.domain.exception.BusinessException;
import com.jaunenoir.infrastructure.repository.CourseRepository;
import com.jaunenoir.infrastructure.repository.NotationRepository;
import com.jaunenoir.infrastructure.repository.UtilisateurRepository;
import com.jaunenoir.service.NotationService;
import com.jaunenoir.shared.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NotationServiceImpl implements NotationService {

    private final NotationRepository notationRepository;
    private final CourseRepository courseRepository;
    private final UtilisateurRepository utilisateurRepository;

    @Override
    @Transactional
    public NotationDto noter(NotationRequest request) {
        Course course = courseRepository.findById(request.courseId())
                .orElseThrow(() -> new BusinessException(Constants.COURSE_NON_TROUVEE));
        Utilisateur auteur = utilisateurRepository.findById(request.auteurId())
                .orElseThrow(() -> new BusinessException(Constants.UTILISATEUR_NON_TROUVE));
        Utilisateur evalue = utilisateurRepository.findById(request.evalueId())
                .orElseThrow(() -> new BusinessException(Constants.UTILISATEUR_NON_TROUVE));

        if (notationRepository.existsByCourseAndAuteur(course, auteur)) {
            throw new BusinessException(Constants.NOTATION_DEJA_SOUMISE);
        }

        Notation notation = Notation.builder()
                .course(course)
                .auteur(auteur)
                .evalue(evalue)
                .note(request.note())
                .commentaire(request.commentaire())
                .build();

        return toDto(notationRepository.save(notation));
    }

    @Override
    public List<NotationDto> findByCourse(UUID courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new BusinessException(Constants.COURSE_NON_TROUVEE));
        return notationRepository.findByCourse(course).stream().map(this::toDto).toList();
    }

    @Override
    public List<NotationDto> findByEvalue(UUID evalueId) {
        Utilisateur evalue = utilisateurRepository.findById(evalueId)
                .orElseThrow(() -> new BusinessException(Constants.UTILISATEUR_NON_TROUVE));
        return notationRepository.findByEvalue(evalue).stream().map(this::toDto).toList();
    }

    @Override
    public Double getMoyenne(UUID utilisateurId) {
        Utilisateur utilisateur = utilisateurRepository.findById(utilisateurId)
                .orElseThrow(() -> new BusinessException(Constants.UTILISATEUR_NON_TROUVE));
        return notationRepository.findMoyenneByEvalue(utilisateur);
    }

    private NotationDto toDto(Notation n) {
        return new NotationDto(
                n.getId(), n.getCourse().getId(),
                n.getAuteur().getId(), n.getAuteur().getPrenom() + " " + n.getAuteur().getNom(),
                n.getEvalue().getId(), n.getEvalue().getPrenom() + " " + n.getEvalue().getNom(),
                n.getNote(), n.getCommentaire(), n.getDateCreation()
        );
    }
}
