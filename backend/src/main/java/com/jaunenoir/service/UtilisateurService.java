package com.jaunenoir.service;

import com.jaunenoir.application.dto.UtilisateurDto;
import com.jaunenoir.application.dto.request.InscriptionRequest;

import java.util.List;
import java.util.UUID;

public interface UtilisateurService {
    UtilisateurDto inscrire(InscriptionRequest request);
    UtilisateurDto findById(UUID id);
    List<UtilisateurDto> findAll();
    UtilisateurDto activerDesactiver(UUID id, boolean actif);
    void supprimer(UUID id);
}
