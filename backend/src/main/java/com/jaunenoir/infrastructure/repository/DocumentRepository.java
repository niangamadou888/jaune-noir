package com.jaunenoir.infrastructure.repository;

import com.jaunenoir.domain.entity.Document;
import com.jaunenoir.domain.entity.Utilisateur;
import com.jaunenoir.domain.enums.TypeDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DocumentRepository extends JpaRepository<Document, UUID> {
    List<Document> findByChauffeur(Utilisateur chauffeur);
    List<Document> findByChauffeurAndValideTrue(Utilisateur chauffeur);
    Optional<Document> findByChauffeurAndTypeDocument(Utilisateur chauffeur, TypeDocument typeDocument);
}
