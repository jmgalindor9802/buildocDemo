package com.buildoc.buildocDemo.entities;

import com.buildoc.buildocDemo.entities.enums.EstadoDato;
import com.buildoc.buildocDemo.entities.enums.InspeccionPeriodicidad;
import lombok.*;
import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Inspeccion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pk_id_inspeccion", columnDefinition = "BIGINT(20)")
    private Long idInspeccion;
    @Enumerated(EnumType.STRING)
    @Column(name = "insPeriodicidad", length = 10)
    private InspeccionPeriodicidad periodicidad;
    @Enumerated(EnumType.STRING)
    private EstadoDato estadoDato;
    @OneToOne(fetch =FetchType.EAGER)
    private Tarea tarea_inspeccion;
    @OneToOne
    private TipoInspeccion tipoInspeccion;

}
