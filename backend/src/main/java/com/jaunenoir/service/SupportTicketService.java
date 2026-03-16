package com.jaunenoir.service;

import com.jaunenoir.application.dto.SupportTicketDto;
import com.jaunenoir.application.dto.request.SupportTicketRequest;
import com.jaunenoir.domain.enums.StatutTicket;

import java.util.List;
import java.util.UUID;

public interface SupportTicketService {
    SupportTicketDto creer(SupportTicketRequest request);
    SupportTicketDto findById(UUID id);
    List<SupportTicketDto> findByUtilisateur(UUID utilisateurId);
    List<SupportTicketDto> findByStatut(StatutTicket statut);
    SupportTicketDto mettreAJourStatut(UUID id, StatutTicket statut);
}
