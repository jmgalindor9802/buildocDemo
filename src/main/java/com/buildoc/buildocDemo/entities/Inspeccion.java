package com.buildoc.buildocDemo.entities;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date; /*Esta clase se utiliza para representar fechas sin hora en Java, similar altipo de dato
DATE en SQL.*/

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "inspecciones")
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
    @Column(name = "fk_id_tarea")
    private Long tarea;

    public enum InspeccionPeriodicidad {
        DIARIA,
        SEMANAL,
        MENSUAL,
        NINGUNA
    }
    @OneToOne(fetch =FetchType.LAZY)
    private Tarea tarea_inspeccion;
    @ManyToOne
    @JoinColumn(name = "fk_id_tipoInspeccion", insertable = false, updatable = false)
    private TipoInspeccion tipoInspeccion;
    @ManyToOne
    @JoinColumn(name = "idProyectoInspeccion")
    private Proyecto proyecto;
}
