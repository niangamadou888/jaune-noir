package com.jaunenoir.infrastructure.repository;

import com.jaunenoir.domain.entity.Course;
import com.jaunenoir.domain.entity.Notation;
import com.jaunenoir.domain.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface NotationRepository extends JpaRepository<Notation, UUID> {
    List<Notation> findByCourse(Course course);
    List<Notation> findByEvalue(Utilisateur evalue);
    boolean existsByCourseAndAuteur(Course course, Utilisateur auteur);

    @Query("SELECT AVG(n.note) FROM Notation n WHERE n.evalue = :utilisateur")
    Double findMoyenneByEvalue(@Param("utilisateur") Utilisateur utilisateur);
}
