package br.mil.ccarj.baseapi.api.controller;

import br.mil.ccarj.baseapi.api.http.resources.request.EscolaRequest;
import br.mil.ccarj.baseapi.api.http.resources.response.EscolaResponse;
import br.mil.ccarj.baseapi.domain.model.Escola;
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
@RequestMapping(value = "/escola")
public class EscolaController extends BaseController{

    private final EscolaService escolaService;

    private final ModelMapper modelMapper;

    public EscolaController (EscolaService escolaService, ModelMapper modelMapper){
        this.escolaService = escolaService;
        this.modelMapper = modelMapper;
    }

    @ApiOperation(value = "Buscar escola por ID", nickname = "getEscolaById", notes = "Retorna uma escola", response = EscolaResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code=200, message = "successful operation", response = EscolaResponse.class),
            @ApiResponse(code=400, message = "Bad Request"),
            @ApiResponse(code=404, message = "Escola not found")})

    @GetMapping(value="/{id}")
    @ResponseBody
    public ResponseEntity<?> findById(@PathVariable(name = "id") Long id) {
        final Escola escolaPorId = escolaService.findById(id);
        EscolaResponse escolaResponse = modelMapper.map(escolaPorId, EscolaResponse.class);
        return respondOk(escolaResponse);

    }

    @ApiOperation(value = "Criar nova escola", nickname = "addEscola", notes = "Criar escola")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request")})

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> create (@RequestBody @Valid EscolaRequest escolaRequest) {
        Escola request = modelMapper.map(escolaRequest, Escola.class);
        Escola created = escolaService.create(request);
        EscolaResponse escolaResponse = modelMapper.map(created, EscolaResponse.class);
        return respondCreated(escolaResponse);
    }

    @ApiOperation(value = "Atualizar Escola existente ", nickname = "updateEscola", notes = "Atualiza Escola", response = EscolaResponse.class)
    @ApiResponses(value = {
    @ApiResponse(code = 204, message = "successful operation", response = EscolaResponse.class),
    @ApiResponse(code = 400, message = "Bad Request"),
    @ApiResponse(code = 404, message = "Escola not found")})

    @PutMapping(value = "/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update (@PathVariable(name = "id") Long id, @RequestBody EscolaRequest escolaRequest) {
        Escola data = modelMapper.map(escolaRequest, Escola.class);
        escolaService.update(id,data);
    }

    @ApiOperation(value = "Deletar Escola existente ", nickname = "deleteEscola", notes = "deleta Escola", response= EscolaResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = EscolaResponse.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Escola not found")})

    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public void delete(@PathVariable (name = "id") Long id) {escolaService.delete(id);}

    @ApiOperation(value = "Buscar Escolas", nickname = "findAll", notes = "Multiple search parameters can be provided", response = Escola.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = Escola.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Bad Request")})

    @GetMapping
    @ResponseBody
    public ResponseEntity<?> findALl(Pageable pageable){
        Page<Escola> escolaPage = escolaService.findAll(pageable);
        List<EscolaResponse> content = escolaPage.stream()
                .map(item -> modelMapper.map(item, EscolaResponse.class))
                .collect(Collectors.toList());
        Page<EscolaResponse> escolaResponses = new PageImpl<>(content, pageable, escolaPage.getTotalElements());
        return respondOk(escolaResponses);
    }
}
