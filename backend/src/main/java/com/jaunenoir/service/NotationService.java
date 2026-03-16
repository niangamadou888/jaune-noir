package com.jaunenoir.service;

import com.jaunenoir.application.dto.NotationDto;
import com.jaunenoir.application.dto.request.NotationRequest;

import java.util.List;
import java.util.UUID;

public interface NotationService {
    NotationDto noter(NotationRequest request);
    List<NotationDto> findByCourse(UUID courseId);
    List<NotationDto> findByEvalue(UUID evalueId);
    Double getMoyenne(UUID utilisateurId);
}
