package br.mil.ccarj.baseapi.api.controller;

import br.mil.ccarj.baseapi.api.http.resources.request.DisciplinaRequest;
import br.mil.ccarj.baseapi.api.http.resources.response.DisciplinaResponse;
import br.mil.ccarj.baseapi.domain.model.Disciplina;
import br.mil.ccarj.baseapi.domain.model.Escola;
import br.mil.ccarj.baseapi.domain.service.DisciplinaService;
import br.mil.ccarj.baseapi.domain.service.EscolaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/disciplina")
public class DisciplinaController extends BaseController {

    private final DisciplinaService disciplinaService;

    private final ModelMapper modelMapper;

    private final EscolaService escolaService;

    public DisciplinaController(DisciplinaService disciplinaService, ModelMapper modelMapper, EscolaService escolaService) {
        this.disciplinaService = disciplinaService;
        this.modelMapper = modelMapper;
        this.escolaService = escolaService;
    }

    @ApiOperation(value = "Buscar disciplina por ID", nickname = "getDisciplinaById", notes = "Retorna uma disciplina", response = DisciplinaResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "successful operation", response = DisciplinaResponse.class), @ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 404, message = "Disciplina not found")})

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<?> findById(@PathVariable(name = "id") Long id) {
        final Disciplina disciplinaPorId = disciplinaService.findById(id);
        DisciplinaResponse disciplinaResponse = modelMapper.map(disciplinaPorId, DisciplinaResponse.class);
        return respondOk(disciplinaResponse);

    }

    @ApiOperation(value = "Criar nova disciplina", nickname = "addDisciplina", notes = "Criar disciplina")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Created"), @ApiResponse(code = 400, message = "Bad Request")})

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody @Valid DisciplinaRequest disciplinaRequest) {

        // CONTINUAR DAQUI
        Disciplina request = modelMapper.map(disciplinaRequest, Disciplina.class);
        Disciplina created = disciplinaService.create(request);
        DisciplinaResponse disciplinaResponse = modelMapper.map(created, DisciplinaResponse.class);
        return respondCreated(disciplinaResponse);
    }

    @ApiOperation(value = "Atualizar Disciplina existente", nickname = "updateDisciplina", notes = "Atualiza Disciplina", response = DisciplinaResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 204, message = "sucessful operation", response = DisciplinaResponse.class), @ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 404, message = "Disciplina not found")})

    @PutMapping(value = "/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable(name = "id") Long id, @RequestBody DisciplinaRequest disciplinaRequest) {
        Disciplina data = modelMapper.map(disciplinaRequest, Disciplina.class);
        disciplinaService.update(id, data);
    }

    @ApiOperation(value = "Deletar Disciplina existente", nickname = "deleteDisciplina", notes = "deleta Disciplina", response = DisciplinaResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "sucessful operation", response = DisciplinaResponse.class), @ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 404, message = "Disciplina not found")})

    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public void delete(@PathVariable(name = "id") Long id) {
        disciplinaService.delete(id);
    }

    @ApiOperation(value = "Buscar Disciplinas", nickname = "findAll", notes = "Pode-se prover mais de um parâmetro de busca", response = Disciplina.class, responseContainer = "List")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "successful operation", response = Disciplina.class, responseContainer = "List"), @ApiResponse(code = 400, message = "Bad Request")})

    @GetMapping
    @ResponseBody
    public ResponseEntity<?> findALl(Pageable pageable) {
        Page<Disciplina> disciplinaPage = disciplinaService.findAll(pageable);
        List<DisciplinaResponse> content = disciplinaPage.stream().map(item -> modelMapper.map(item, DisciplinaResponse.class)).collect(Collectors.toList());
        Page<DisciplinaResponse> disciplinaResponses = new PageImpl<>(content, pageable, disciplinaPage.getTotalElements());
        return respondOk(disciplinaResponses);
    }
}