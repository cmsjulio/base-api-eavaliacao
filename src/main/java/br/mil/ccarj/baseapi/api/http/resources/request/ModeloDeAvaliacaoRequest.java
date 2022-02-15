package br.mil.ccarj.baseapi.api.http.resources.request;

import br.mil.ccarj.baseapi.api.http.resources.response.ProcessoAvaliativoResponse;
import br.mil.ccarj.baseapi.domain.model.ProcessoAvaliativo;
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
public class ModeloDeAvaliacaoRequest {

    @ApiModelProperty(value = "Nome")
    private String nome;

    @ApiModelProperty(value = "Sigla")
    private String sigla;

    @ApiModelProperty(value = "Descricao")
    private String descricao;

    //NEW - 14/FEV/2022
    @ApiModelProperty(value = "Processo Avaliativo")
    private ProcessoAvaliativoResponse processosAvaliativos;


}
