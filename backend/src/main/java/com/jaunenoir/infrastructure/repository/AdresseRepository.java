package com.jaunenoir.infrastructure.repository;

import com.jaunenoir.domain.entity.Adresse;
import com.jaunenoir.domain.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AdresseRepository extends JpaRepository<Adresse, UUID> {
    List<Adresse> findByUtilisateur(Utilisateur utilisateur);
}
