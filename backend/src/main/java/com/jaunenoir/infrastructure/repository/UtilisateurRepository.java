package com.jaunenoir.infrastructure.repository;

import com.jaunenoir.domain.entity.Utilisateur;
import com.jaunenoir.domain.enums.RoleUtilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, UUID> {
    Optional<Utilisateur> findByTelephone(String telephone);
    Optional<Utilisateur> findByEmail(String email);
    List<Utilisateur> findByRole(RoleUtilisateur role);
    boolean existsByTelephone(String telephone);
    boolean existsByEmail(String email);
}
