package br.mil.ccarj.baseapi.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "T_PROCESSO_AVALIATIVO")
@Audited
@Data
@SuperBuilder
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
public class ProcessoAvaliativo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_PROCESSO_AVALIATIVO")
    private Long id;

    @Column(name = "NM_PROCESSO_AVALIATIVO", nullable = false)
    private String nome;

//    @OneToOne(mappedBy = "processoAvaliativo")
//    private List<ModeloDeAvaliacao> modeloDeAvaliacoes;
}
