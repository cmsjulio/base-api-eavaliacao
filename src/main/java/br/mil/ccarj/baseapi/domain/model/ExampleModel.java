package br.mil.ccarj.baseapi.domain.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Table(name = "T_EXAMPLE_MODEL")
@Audited
@Data
@SuperBuilder
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
public class ExampleModel extends BaseModel{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_EXEMPLO")
    private Long id;

    @Column(name = "SG_EXEMPLO", nullable = false)
    private String sigla;

    @Column(name = "NM_EXEMPLO", nullable = false)
    private String nome;
}
