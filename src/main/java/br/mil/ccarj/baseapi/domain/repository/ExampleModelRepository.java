package br.mil.ccarj.baseapi.domain.repository;

import br.mil.ccarj.baseapi.domain.model.ExampleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

public interface ExampleModelRepository extends RevisionRepository<ExampleModel, Long, Integer>, JpaRepository<ExampleModel, Long> {
}
