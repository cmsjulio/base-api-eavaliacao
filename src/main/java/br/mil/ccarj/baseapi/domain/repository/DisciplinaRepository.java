package br.mil.ccarj.baseapi.domain.repository;

import br.mil.ccarj.baseapi.domain.model.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

public interface DisciplinaRepository extends RevisionRepository<Disciplina, Long, Integer>, JpaRepository<Disciplina, Long> {
}
