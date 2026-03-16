package com.jaunenoir.infrastructure.repository;

import com.jaunenoir.domain.entity.Course;
import com.jaunenoir.domain.entity.Utilisateur;
import com.jaunenoir.domain.enums.StatutCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CourseRepository extends JpaRepository<Course, UUID> {
    List<Course> findByPassagerOrderByDateCreationDesc(Utilisateur passager);
    List<Course> findByChauffeurOrderByDateCreationDesc(Utilisateur chauffeur);
    List<Course> findByStatut(StatutCourse statut);
}
