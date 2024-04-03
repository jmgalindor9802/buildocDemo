package com.buildoc.buildocDemo.entities;

import com.buildoc.buildocDemo.entities.enums.EstadoDato;
import com.buildoc.buildocDemo.entities.enums.EstadoTarea;
import lombok.*;
import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Tarea {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long idTarea;
    private String nombre;
    private String descripcion;
    @Enumerated(EnumType.STRING)
    private EstadoTarea estado;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaInicial;
    private LocalDateTime fechaLimite;
    @Enumerated(EnumType.STRING)
    private EstadoDato estadoDato;

    @ManyToOne
    private Ciclo ciclo;

    @OneToOne(cascade = CascadeType.ALL, fetch =FetchType.LAZY, orphanRemoval = true)
    private Inspeccion inspeccion;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Archivo> archivos;

    @ManyToMany(fetch =FetchType.LAZY)
    private List<Usuario> usuario;


    @OneToMany(fetch =FetchType.LAZY)
    private List<RespuestaTarea> respuestasTarea;



}
