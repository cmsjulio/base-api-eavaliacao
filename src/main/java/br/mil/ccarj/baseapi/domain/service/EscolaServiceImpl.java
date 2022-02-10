package br.mil.ccarj.baseapi.domain.service;

import br.mil.ccarj.baseapi.domain.exception.NotFoundException;
import br.mil.ccarj.baseapi.domain.model.Escola;
import br.mil.ccarj.baseapi.domain.repository.EscolaRepository;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EscolaServiceImpl implements EscolaService {

    private final EscolaRepository escolaRepository;

    public EscolaServiceImpl (EscolaRepository escolaRepository) {this.escolaRepository = escolaRepository;}

    @Override
    public void teste() {}

    @Override
    public Escola create (Escola escola) {return escolaRepository.save(escola);}

    @Override
    public Escola findById(Long id) {
        return escolaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Escola não encontrada"));
    }

    @Override
    public Page<Escola> findAll(Pageable pageable) {return escolaRepository.findAll(pageable);}

    @Override
    public void update (Long id, Escola escola){
        Escola escolaPorId = findById(id);

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());

        mapper.map(escola, escolaPorId);

        escolaRepository.save(escolaPorId);
    }

    @Override
    public void delete (Long id) {
        Escola escolaPorId = findById(id);
        escolaRepository.delete(escolaPorId);
    }

}