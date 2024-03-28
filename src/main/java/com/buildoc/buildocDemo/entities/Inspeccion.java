package com.buildoc.buildocDemo.entities;

import com.buildoc.buildocDemo.entities.enums.InspeccionPeriodicidad;
import lombok.*;
import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inspeccion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pk_id_inspeccion", columnDefinition = "BIGINT(20)")
    private Long idInspeccion;
    /*Esta anotación se utiliza junto a la definición de un enum para indicar cómo se almacenará el valor del
    enum en la base de datos. En este caso, EnumType.STRING indica que se almacenará el nombre del valor del
    enum como una cadena de texto.*/
    @Enumerated(EnumType.STRING)
    @Column(name = "insPeriodicidad", length = 10)
    private InspeccionPeriodicidad periodicidad;

    @OneToOne(fetch =FetchType.LAZY)
    private Tarea tarea_inspeccion;
    @OneToOne
    private TipoInspeccion tipoInspeccion;

}
