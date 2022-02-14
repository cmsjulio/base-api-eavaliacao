package br.mil.ccarj.baseapi.api.http.resources.request;

import br.mil.ccarj.baseapi.api.http.resources.response.ModeloDeAvaliacaoResponse;
import br.mil.ccarj.baseapi.domain.model.ModeloDeAvaliacao;
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
public class ProcessoAvaliativoRequest {
    @ApiModelProperty(value = "Nome")
    private String nome;

    @ApiModelProperty(value = "Modelo de Avaliação")
    private ModeloDeAvaliacao modeloDeAvaliacao;
}
