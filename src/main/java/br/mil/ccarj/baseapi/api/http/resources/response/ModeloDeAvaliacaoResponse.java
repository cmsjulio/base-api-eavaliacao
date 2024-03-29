package br.mil.ccarj.baseapi.api.http.resources.response;

import br.mil.ccarj.baseapi.domain.model.Periodo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ModeloDeAvaliacaoResponse {

    @ApiModelProperty(value = "Id")
    private Long id;

    @ApiModelProperty(value = "Nome")
    private String nome;

    @ApiModelProperty(value = "Sigla")
    private String sigla;

    @ApiModelProperty(value = "Descricao")
    private String descricao;

    @ApiModelProperty(value = "Periodos")
    private List<Periodo> periodos;

    //NEW - 14/FEV/2022
    @ApiModelProperty(value = "Processo Avaliativo")
    private ProcessoAvaliativoResponse processoAvaliativo;
}
