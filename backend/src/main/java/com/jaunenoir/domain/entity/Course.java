package com.jaunenoir.domain.entity;

import com.jaunenoir.domain.enums.ModePaiement;
import com.jaunenoir.domain.enums.StatutCourse;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.locationtech.jts.geom.Point;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "passager_id", nullable = false)
    private Utilisateur passager;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chauffeur_id")
    private Utilisateur chauffeur;

    @Column(columnDefinition = "geometry(Point,4326)")
    private Point depart;

    @Column(columnDefinition = "geometry(Point,4326)")
    private Point arrivee;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private StatutCourse statut = StatutCourse.EN_ATTENTE;

    @Column(precision = 10, scale = 2)
    private BigDecimal prix;

    private String codePin;

    @Enumerated(EnumType.STRING)
    private ModePaiement modePaiement;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime dateCreation;
}
