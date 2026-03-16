package com.jaunenoir.service.impl;

import com.jaunenoir.application.dto.UtilisateurDto;
import com.jaunenoir.application.dto.request.InscriptionRequest;
import com.jaunenoir.domain.entity.Utilisateur;
import com.jaunenoir.domain.exception.BusinessException;
import com.jaunenoir.infrastructure.repository.UtilisateurRepository;
import com.jaunenoir.service.UtilisateurService;
import com.jaunenoir.shared.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UtilisateurServiceImpl implements UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UtilisateurDto inscrire(InscriptionRequest request) {
        if (utilisateurRepository.existsByTelephone(request.telephone())) {
            throw new BusinessException(Constants.TELEPHONE_DEJA_UTILISE);
        }
        if (request.email() != null && utilisateurRepository.existsByEmail(request.email())) {
            throw new BusinessException(Constants.EMAIL_DEJA_UTILISE);
        }

        Utilisateur utilisateur = Utilisateur.builder()
                .nom(request.nom())
                .prenom(request.prenom())
                .telephone(request.telephone())
                .email(request.email())
                .role(request.role())
                .motDePasse(passwordEncoder.encode(request.motDePasse()))
                .build();

        return toDto(utilisateurRepository.save(utilisateur));
    }

    @Override
    public UtilisateurDto findById(UUID id) {
        return toDto(getOrThrow(id));
    }

    @Override
    public List<UtilisateurDto> findAll() {
        return utilisateurRepository.findAll().stream().map(this::toDto).toList();
    }

    @Override
    @Transactional
    public UtilisateurDto activerDesactiver(UUID id, boolean actif) {
        Utilisateur utilisateur = getOrThrow(id);
        utilisateur.setActif(actif);
        return toDto(utilisateurRepository.save(utilisateur));
    }

    @Override
    @Transactional
    public void supprimer(UUID id) {
        getOrThrow(id);
        utilisateurRepository.deleteById(id);
    }

    private Utilisateur getOrThrow(UUID id) {
        return utilisateurRepository.findById(id)
                .orElseThrow(() -> new BusinessException(Constants.UTILISATEUR_NON_TROUVE));
    }

    private UtilisateurDto toDto(Utilisateur u) {
        return new UtilisateurDto(
                u.getId(), u.getNom(), u.getPrenom(),
                u.getTelephone(), u.getEmail(),
                u.getRole(), u.isActif(), u.getDateCreation()
        );
    }
}
