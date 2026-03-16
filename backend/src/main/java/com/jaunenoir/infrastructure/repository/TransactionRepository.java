package com.jaunenoir.infrastructure.repository;

import com.jaunenoir.domain.entity.Course;
import com.jaunenoir.domain.entity.Transaction;
import com.jaunenoir.domain.enums.StatutTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    Optional<Transaction> findByReference(String reference);
    List<Transaction> findByCourse(Course course);
    List<Transaction> findByStatut(StatutTransaction statut);
}
