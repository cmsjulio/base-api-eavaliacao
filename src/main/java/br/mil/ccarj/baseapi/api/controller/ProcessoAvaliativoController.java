package br.mil.ccarj.baseapi.api.controller;

import br.mil.ccarj.baseapi.api.http.resources.request.ProcessoAvaliativoRequest;
import br.mil.ccarj.baseapi.api.http.resources.response.ProcessoAvaliativoResponse;
import br.mil.ccarj.baseapi.domain.model.ModeloDeAvaliacao;
import br.mil.ccarj.baseapi.domain.model.ProcessoAvaliativo;
import br.mil.ccarj.baseapi.domain.service.ModeloDeAvaliacaoService;
import br.mil.ccarj.baseapi.domain.service.ModeloDeAvaliacaoServiceImpl;
import br.mil.ccarj.baseapi.domain.service.ProcessoAvaliativoService;
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
@RequestMapping("/processoAvaliativo")
public class ProcessoAvaliativoController extends BaseController{

    private final ProcessoAvaliativoService service;
    private final ModelMapper modelMapper;
    private final ModeloDeAvaliacaoService modeloDeAvaliacaoService;

    public ProcessoAvaliativoController(ProcessoAvaliativoService service, ModelMapper modelMapper, ModeloDeAvaliacaoService modeloDeAvaliacaoService){
        this.service = service;
        this.modelMapper = modelMapper;
        this.modeloDeAvaliacaoService = modeloDeAvaliacaoService;
    }

    @ApiOperation(value = "Buscar Processo Avaliativo por ID", nickname = "getProcessoAvaliativoById", notes = "Retorna um Processo Avaliativo", response = ProcessoAvaliativoResponse.class)
    @ApiResponses(value ={
            @ApiResponse(code = 200, message = "successful operation", response = ProcessoAvaliativoResponse.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Processo Avaliativo não encontrado.")})

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<?> findById(@PathVariable(name = "id") Long id){
        final ProcessoAvaliativo processoAvaliativo = service.findById(id);
        ProcessoAvaliativoResponse response = modelMapper.map(processoAvaliativo, ProcessoAvaliativoResponse.class);
        return respondOk(response);
    }

    @ApiOperation(value = "Criar novo Processo Avaliativo.", nickname = "addProcessoAvaliativo", notes = "Criar Processo Avaliativo")
    @ApiResponses(value ={
            @ApiResponse(code = 201, message = "Created."),
            @ApiResponse(code = 400, message = "Bad Request.")})

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> create (@RequestBody @Valid ProcessoAvaliativoRequest processoAvaliativoRequest) {
        ModeloDeAvaliacao modeloDeAvaliacao = modeloDeAvaliacaoService.findById(processoAvaliativoRequest.getModeloDeAvaliacao().getId());
        ProcessoAvaliativo request = modelMapper.map(processoAvaliativoRequest, ProcessoAvaliativo.class);
        ProcessoAvaliativo created = service.create(request);
        created.setModeloDeAvaliacao(modeloDeAvaliacao);
        ProcessoAvaliativoResponse response = modelMapper.map(created, ProcessoAvaliativoResponse.class);
        return respondCreated(response);
    }

    @ApiOperation(value = "Atualizar Processo Avaliativo existente.", nickname = "updateProcessoAvaliativo", notes = "Atualiza Processo Avaliativo.", response = ProcessoAvaliativoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "successful operation", response = ProcessoAvaliativoResponse.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Processo Avaliativo not found.")
    })

    @PutMapping(value = "/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable(name = "id") Long id, @RequestBody ProcessoAvaliativoRequest request) {
        ProcessoAvaliativo data = modelMapper.map(request, ProcessoAvaliativo.class);
        service.update(id, data);
    }

    @ApiOperation(value = "Deletar Processo Avaliativo existente.", nickname = "deleteProcessoAvaliativo", notes = "Deleta Processo Avaliativo.", response = ProcessoAvaliativoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "successful operation.", response = ProcessoAvaliativoResponse.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Processo Avaliativo not found.")
    })

    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public void delete(@PathVariable(name = "id") Long id) {service.delete(id);}

    @ApiOperation(value = "Buscar Processo Avaliativo.", nickname = "findAll", notes = "Parâmetros múltiplos podem ser fornecidos.", response = ProcessoAvaliativo.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = ProcessoAvaliativo.class, responseContainer = "List"),
            @ApiResponse(code =400, message = "Bad Request")
    })

    @GetMapping
    @ResponseBody
    public ResponseEntity<?> findAll(Pageable pageable){
        Page<ProcessoAvaliativo> processoAvaliativoPage = service.findAll(pageable);
        List<ProcessoAvaliativoResponse> content = processoAvaliativoPage.stream()
                .map(item -> modelMapper.map(item, ProcessoAvaliativoResponse.class))
                .collect(Collectors.toList());
        Page<ProcessoAvaliativoResponse> processoAvaliativoResponses = new PageImpl<>(content, pageable, processoAvaliativoPage.getTotalElements());
        return respondOk(processoAvaliativoResponses);

    }

































}
