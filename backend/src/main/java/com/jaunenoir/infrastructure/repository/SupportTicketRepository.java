package com.jaunenoir.infrastructure.repository;

import com.jaunenoir.domain.entity.SupportTicket;
import com.jaunenoir.domain.entity.Utilisateur;
import com.jaunenoir.domain.enums.StatutTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SupportTicketRepository extends JpaRepository<SupportTicket, UUID> {
    List<SupportTicket> findByUtilisateurOrderByDateCreationDesc(Utilisateur utilisateur);
    List<SupportTicket> findByStatut(StatutTicket statut);
}
