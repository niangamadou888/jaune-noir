package com.jaunenoir.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "adresses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Adresse {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String libelle;

    private String rue;

    @Column(nullable = false)
    private String ville;

    private String codePostal;

    private Double latitude;

    private Double longitude;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;
}
