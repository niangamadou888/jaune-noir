package com.jaunenoir.service.impl;

import com.jaunenoir.application.dto.SupportTicketDto;
import com.jaunenoir.application.dto.request.SupportTicketRequest;
import com.jaunenoir.domain.entity.SupportTicket;
import com.jaunenoir.domain.entity.Utilisateur;
import com.jaunenoir.domain.enums.StatutTicket;
import com.jaunenoir.domain.exception.BusinessException;
import com.jaunenoir.infrastructure.repository.SupportTicketRepository;
import com.jaunenoir.infrastructure.repository.UtilisateurRepository;
import com.jaunenoir.service.SupportTicketService;
import com.jaunenoir.shared.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SupportTicketServiceImpl implements SupportTicketService {

    private final SupportTicketRepository ticketRepository;
    private final UtilisateurRepository utilisateurRepository;

    @Override
    @Transactional
    public SupportTicketDto creer(SupportTicketRequest request) {
        Utilisateur utilisateur = utilisateurRepository.findById(request.utilisateurId())
                .orElseThrow(() -> new BusinessException(Constants.UTILISATEUR_NON_TROUVE));

        SupportTicket ticket = SupportTicket.builder()
                .utilisateur(utilisateur)
                .sujet(request.sujet())
                .description(request.description())
                .build();

        return toDto(ticketRepository.save(ticket));
    }

    @Override
    public SupportTicketDto findById(UUID id) {
        return toDto(getOrThrow(id));
    }

    @Override
    public List<SupportTicketDto> findByUtilisateur(UUID utilisateurId) {
        Utilisateur utilisateur = utilisateurRepository.findById(utilisateurId)
                .orElseThrow(() -> new BusinessException(Constants.UTILISATEUR_NON_TROUVE));
        return ticketRepository.findByUtilisateurOrderByDateCreationDesc(utilisateur)
                .stream().map(this::toDto).toList();
    }

    @Override
    public List<SupportTicketDto> findByStatut(StatutTicket statut) {
        return ticketRepository.findByStatut(statut).stream().map(this::toDto).toList();
    }

    @Override
    @Transactional
    public SupportTicketDto mettreAJourStatut(UUID id, StatutTicket statut) {
        SupportTicket ticket = getOrThrow(id);
        ticket.setStatut(statut);
        return toDto(ticketRepository.save(ticket));
    }

    private SupportTicket getOrThrow(UUID id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new BusinessException(Constants.TICKET_NON_TROUVE));
    }

    private SupportTicketDto toDto(SupportTicket t) {
        return new SupportTicketDto(
                t.getId(),
                t.getUtilisateur().getId(),
                t.getUtilisateur().getPrenom() + " " + t.getUtilisateur().getNom(),
                t.getSujet(), t.getDescription(),
                t.getStatut(), t.getDateCreation(), t.getDateMiseAJour()
        );
    }
}
