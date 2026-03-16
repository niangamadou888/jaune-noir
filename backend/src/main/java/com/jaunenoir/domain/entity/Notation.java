package com.jaunenoir.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "notations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auteur_id", nullable = false)
    private Utilisateur auteur;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "evalue_id", nullable = false)
    private Utilisateur evalue;

    @Column(nullable = false)
    @Min(1)
    @Max(5)
    private Integer note;

    @Column(length = 500)
    private String commentaire;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime dateCreation;
}
