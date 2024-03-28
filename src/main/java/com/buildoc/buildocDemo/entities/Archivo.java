package com.buildoc.buildocDemo.entities;

import lombok.*;
import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Archivo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idArchivo;
    private String nombreOriginal;
    private LocalDateTime fechaCreacion;
    private String tipo;
    private String tamano;
    private String ruta;

    @ManyToOne(cascade = CascadeType.ALL)
    private Usuario usuario;

    @ManyToMany(mappedBy = "archivos")
    private List<TipoInspeccion> tipoInspecciones;

    @ManyToMany(mappedBy = "archivos")
    private List<Tarea> tareas;

}
