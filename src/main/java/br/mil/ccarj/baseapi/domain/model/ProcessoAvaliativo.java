package br.mil.ccarj.baseapi.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    // NEW - 14/FEV/2022

    @OneToOne(mappedBy = "processoAvaliativo")
    private ModeloDeAvaliacao modeloDeAvaliacao;

}
