package com.buildoc.buildocDemo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table (name="ciclos")
public class Ciclo {
    @Column (name="pk_id_ciclo")
    private Long idCiclo;
    @Column(name="cicNombre")
    private String nombre;
    @Column (name="cicFecha_creacion")
    private LocalDateTime fechaCreacion;
    @Column (name="cicDescripcion")
    private String descripcion;
    @Enumerated(EnumType.STRING)
    @Column (name="cicEstado")
    private EstadoCiclo estado;
    @Column (name="fk_id_proyecto")
    private Long idProyecto;

    @ManyToOne
    @JoinColumn(name="fk_id_proyecto", insertable = false,updatable = false)
    private Proyecto proyecto;

    @OneToMany(mappedBy = "ciclo",cascade = CascadeType.ALL, fetch =FetchType.LAZY)
    private List<Tarea> tareas;


}
