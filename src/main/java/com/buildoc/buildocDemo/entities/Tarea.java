package com.buildoc.buildocDemo.entities;

import com.buildoc.buildocDemo.entities.enums.EstadoTarea;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table (name="tareas")
public class Tarea {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="pk_id_tarea")
    private Long idTarea;
    @Column (name="tarNombre")
    private String nombre;
    @Column (name="tarDescripcion")
    private String descripcion;
    @Enumerated(EnumType.STRING)
    @Column (name="tarEstado")
    private EstadoTarea estado;
    @Column(name="tarFechaCreacion")
    private LocalDateTime fechaCreacion;
    @Column (name = "tarFecha_limite")
    private LocalDateTime fechaLimite;

    @ManyToOne
    private Ciclo ciclo;

    @OneToOne(mappedBy = "tarea_inspeccion",cascade = CascadeType.ALL, fetch =FetchType.LAZY, orphanRemoval = true)
    private Inspeccion inspeccion;
    @ManyToMany
    @JoinTable(name = "tareas_archivos", joinColumns = @JoinColumn(name = "fk_id_tarea"),
            inverseJoinColumns = @JoinColumn(name = "fk_id_archivo"))
    private List<Archivo> archivos;
}
