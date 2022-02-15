package br.mil.ccarj.baseapi.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Table(name = "T_DISCIPLINA")
@Audited
@Data
@SuperBuilder
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
public class Disciplina extends BaseModel{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_DISCIPLINA")
    private Long id;

    @Column(name = "SG_DISCIPLINA", nullable = false)
    private String sigla;

    @Column(name = "NM_DISCIPLINA", nullable = false)
    private String nome;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "escola_id")
    private Escola escola;

}
