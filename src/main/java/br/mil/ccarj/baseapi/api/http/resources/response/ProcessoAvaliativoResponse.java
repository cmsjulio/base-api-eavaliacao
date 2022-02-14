package br.mil.ccarj.baseapi.api.http.resources.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProcessoAvaliativoResponse {

    @ApiModelProperty(value = "Id")
    private Long id;

    @ApiModelProperty(value = "Nome")
    private String nome;

    // NEW - 14/FEV/2022
    @ApiModelProperty(value = "Modelo De Avaliacao")
    private ModeloDeAvaliacaoResponse modeloDeAvalicao;

}
