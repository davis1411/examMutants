package com.mercadolibre.mutants.persistence.repository;

import com.mercadolibre.mutants.persistence.entities.Dna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultDnaRepository extends JpaRepository<Dna, Long> {
    List<Dna> findByIsMutant(boolean isMutant);
}
