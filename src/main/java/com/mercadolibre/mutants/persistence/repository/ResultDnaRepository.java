package com.mercadolibre.mutants.persistence.repository;

import com.mercadolibre.mutants.persistence.entities.Dna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultDnaRepository extends JpaRepository<Dna, Long> {
}
