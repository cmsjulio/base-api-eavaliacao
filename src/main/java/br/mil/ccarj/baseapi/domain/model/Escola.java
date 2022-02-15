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
@Table(name = "T_ESCOLA")
@Audited
@Data
@SuperBuilder
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
public class Escola extends BaseModel{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_ESCOLA")
    private Long id;

    @Column(name = "SG_ESCOLA", nullable = false)
    private String sigla;

    @Column(name = "NM_ESCOLA", nullable = false)
    private String nome;

    @OneToMany(mappedBy = "escola")
    private List<Disciplina> disciplinas;

}