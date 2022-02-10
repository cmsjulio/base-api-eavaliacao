package br.mil.ccarj.baseapi.domain.service;

import br.mil.ccarj.baseapi.domain.exception.NotFoundException;
import br.mil.ccarj.baseapi.domain.model.Disciplina;
import br.mil.ccarj.baseapi.domain.repository.DisciplinaRepository;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DisciplinaServiceImpl implements DisciplinaService{

    private final DisciplinaRepository disciplinaRepository;

    public DisciplinaServiceImpl (DisciplinaRepository disciplinaRepository) {this.disciplinaRepository = disciplinaRepository;}

    @Override
    public void teste(){}

    @Override
    public Disciplina create (Disciplina disciplina) {return disciplinaRepository.save(disciplina);}

    @Override
    public Disciplina findById(Long id) {
        return disciplinaRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Disciplina não encontrada"));

    }

    @Override
    public Page<Disciplina> findAll (Pageable pageable) {return disciplinaRepository.findAll(pageable);}

    @Override
    public void update (Long id, Disciplina disciplina) {
        Disciplina disciplinaPorId = findById(id);

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());

        mapper.map(disciplina, disciplinaPorId);

        disciplinaRepository.save(disciplinaPorId);
    }

    @Override
    public void delete (Long id){
        Disciplina disciplinaPorId = findById(id);
        disciplinaRepository.delete(disciplinaPorId);
    }
}
