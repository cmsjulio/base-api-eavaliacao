package br.mil.ccarj.baseapi.domain.repository;

import br.mil.ccarj.baseapi.domain.model.Escola;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

public interface EscolaRepository extends RevisionRepository<Escola, Long, Integer>, JpaRepository<Escola, Long> {
}
