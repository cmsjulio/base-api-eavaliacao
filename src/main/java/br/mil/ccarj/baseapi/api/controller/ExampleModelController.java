package br.mil.ccarj.baseapi.api.controller;


import br.mil.ccarj.baseapi.api.http.resources.request.ExampleModelRequest;
import br.mil.ccarj.baseapi.api.http.resources.response.ExampleModelResponse;
import br.mil.ccarj.baseapi.domain.model.ExampleModel;
import br.mil.ccarj.baseapi.domain.service.ExampleModelService;
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
@RequestMapping(value = "/exampleModel")
public class ExampleModelController extends BaseController {

    private final ExampleModelService service;

    private final ModelMapper modelMapper;

    public ExampleModelController(ExampleModelService service, ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @ApiOperation(value = "Buscar exemplo por ID", nickname = "getExemploById", notes = "Returns a single Exemplo", response = ExampleModelResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = ExampleModelResponse.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Exemplo not found")})

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<?> findById(@PathVariable(name = "id") Long id) {
        final ExampleModel exampleModel = service.findById(id);
        ExampleModelResponse response = modelMapper.map(exampleModel, ExampleModelResponse.class);
        return respondOk(response);

    }


   @ApiOperation(value = "Criar novo exemplo", nickname = "addExemplo", notes = "Criar exemplo")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request")})

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody @Valid ExampleModelRequest exampleModelRequest) {
       ExampleModel request = modelMapper.map(exampleModelRequest, ExampleModel.class);
       ExampleModel created = service.create(request);
       ExampleModelResponse response = modelMapper.map(created, ExampleModelResponse.class);
        return respondCreated(response);
    }


    @ApiOperation(value = "Atualizar ExampleModel existente ", nickname = "updateExampleModel", notes = "Atualiza ExampleModel", response = ExampleModelResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "successful operation", response = ExampleModelResponse.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "ExampleModel not found")})

    @PutMapping(value = "/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable(name = "id") Long id, @RequestBody ExampleModelRequest request) {
        ExampleModel data = modelMapper.map(request, ExampleModel.class);
        service.update(id, data);
    }


    @ApiOperation(value = "Deletar ExampleModel existente ", nickname = "deleteExampleModel", notes = "deleta ExampleModel", response = ExampleModelResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = ExampleModelResponse.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "ExampleModel not found")})

    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public void delete(@PathVariable(name = "id") Long id) {
        service.delete(id);
    }


    @ApiOperation(value = "Buscar ExampleModels", nickname = "findAll", notes = "Multiple search parasm can be provided", response = ExampleModel.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = ExampleModel.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Bad Request")})

    @GetMapping
    @ResponseBody
    public ResponseEntity<?> findAll(Pageable pageable) {
        Page<ExampleModel> exampleModelPage = service.findAll(pageable);
        List<ExampleModelResponse> content = exampleModelPage.stream()
                .map(item -> modelMapper.map(item, ExampleModelResponse.class))
                .collect(Collectors.toList());
        Page<ExampleModelResponse> exampleModelResponses = new PageImpl<>(content, pageable, exampleModelPage.getTotalElements());
        return respondOk(exampleModelResponses);
    }

}
