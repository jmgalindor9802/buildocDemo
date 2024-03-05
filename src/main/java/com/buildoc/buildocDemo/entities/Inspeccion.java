package com.buildoc.buildocDemo.entities;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date; /*Esta clase se utiliza para representar fechas sin hora en Java, similar altipo de dato
DATE en SQL.*/

@Entity
@Table(name = "inspecciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inspeccion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pk_id_inspeccion", columnDefinition = "BIGINT(20)")
    private Long idInspeccion;

    @Column(name = "insNombre", nullable = false, length = 280)
    private String nombre;

    @Column(name = "insDescripcion", nullable = false, length = 5000)
    private String descripcion;

    /*Esta anotación se utiliza junto a la definición de un enum para indicar cómo se almacenará el valor del
    enum en la base de datos. En este caso, EnumType.STRING indica que se almacenará el nombre del valor del
    enum como una cadena de texto.*/
    @Enumerated(EnumType.STRING)
    @Column(name = "Estado", nullable = false, length = 20)
    private InspeccionEstado estado;

    @Column(name = "Fecha_inicial", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicial;

    /*Esta anotación se utiliza junto a la definición de un enum para indicar cómo se almacenará el valor del
    enum en la base de datos. En este caso, EnumType.STRING indica que se almacenará el nombre del valor del
    enum como una cadena de texto.*/
    @Enumerated(EnumType.STRING)
    @Column(name = "insPeriodicidad", length = 10)
    private InspeccionPeriodicidad periodicidad;

    @Column(name = "insFecha_final")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFinal;

    @Column(name = "insFecha_creacion", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;

    @Column(name = "fk_id_usuario", nullable = false)
    private Long idUsuario;

    @Column(name = "fk_id_proyecto", nullable = false)
    private Long idProyecto;

    public enum InspeccionEstado {
        PENDIENTE,
        EN_PROGRESO,
        COMPLETADO
    }

    public enum InspeccionPeriodicidad {
        DIARIA,
        SEMANAL,
        MENSUAL,
        NINGUNA
    }
}
