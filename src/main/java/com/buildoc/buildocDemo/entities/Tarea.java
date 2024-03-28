package com.buildoc.buildocDemo.entities;

import com.buildoc.buildocDemo.entities.enums.EstadoTarea;
import lombok.*;
import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Tarea {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long idTarea;

    private String nombre;

    private String descripcion;
    @Enumerated(EnumType.STRING)

    private EstadoTarea estado;

    private LocalDateTime fechaCreacion;

    private LocalDateTime fechaLimite;

    @ManyToOne
    private Ciclo ciclo;

    @OneToOne(cascade = CascadeType.ALL, fetch =FetchType.LAZY, orphanRemoval = true)
    private Inspeccion inspeccion;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Archivo> archivos;
}
