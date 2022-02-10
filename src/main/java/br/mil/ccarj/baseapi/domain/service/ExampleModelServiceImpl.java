package br.mil.ccarj.baseapi.domain.service;

import br.mil.ccarj.baseapi.domain.exception.NotFoundException;
import br.mil.ccarj.baseapi.domain.model.ExampleModel;
import br.mil.ccarj.baseapi.domain.repository.ExampleModelRepository;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ExampleModelServiceImpl implements ExampleModelService {

    private final ExampleModelRepository repository;

    public ExampleModelServiceImpl(ExampleModelRepository repository) {
        this.repository = repository;
    }

    @Override
    public void teste() {
    }

    @Override
    public ExampleModel create(ExampleModel entity) {
        return repository.save(entity);
    }

    @Override
    public ExampleModel findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Organização não encontrada"));
    }

    @Override
    public Page<ExampleModel> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public void update(Long id, ExampleModel entity) {
        ExampleModel exampleModel = findById(id);

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());

        mapper.map(entity, exampleModel);

        repository.save(exampleModel);
    }

    @Override
    public void delete(Long id) {
        ExampleModel exampleModel = findById(id);
        repository.delete(exampleModel);
    }
}
