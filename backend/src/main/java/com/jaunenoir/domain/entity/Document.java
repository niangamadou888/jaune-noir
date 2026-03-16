package com.jaunenoir.domain.entity;

import com.jaunenoir.domain.enums.TypeDocument;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "documents")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chauffeur_id", nullable = false)
    private Utilisateur chauffeur;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeDocument typeDocument;

    @Column(nullable = false)
    private String urlDocument;

    private String nomFichier;

    private LocalDate dateExpiration;

    @Builder.Default
    private boolean valide = false;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime dateCreation;
}
