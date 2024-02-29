package com.buildoc.buildocDemo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table (name="tareas")
public class Tarea {
    @Column(name="pk_id_tarea")
    private Long idTarea;
    @Column (name="tarNombre")
    private String nombre;
    @Column (name="tarDescripcion")
    private String descripcion;
    @Enumerated(EnumType.STRING)
    @Column (name="tarPrioridad")
    private EstadoTarea estado;

    @Column(name="tarFecha_creacion")
    private LocalDateTime fechaCreacion;
    @Column (name = "tarFecha_limite")
    private LocalDateTime fechaLimite;
    @Column (name="fk_id_ciclo")
    private Long idCiclo;

    @ManyToOne
    @JoinColumn(name="fk_id_ciclo",insertable = false,updatable = false)
    private Ciclo ciclo;


}
