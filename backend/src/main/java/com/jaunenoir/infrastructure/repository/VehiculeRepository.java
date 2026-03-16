package com.jaunenoir.infrastructure.repository;

import com.jaunenoir.domain.entity.Utilisateur;
import com.jaunenoir.domain.entity.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface VehiculeRepository extends JpaRepository<Vehicule, UUID> {
    List<Vehicule> findByChauffeur(Utilisateur chauffeur);
    List<Vehicule> findByChauffeurAndActifTrue(Utilisateur chauffeur);
    Optional<Vehicule> findByImmatriculation(String immatriculation);
    boolean existsByImmatriculation(String immatriculation);
}
