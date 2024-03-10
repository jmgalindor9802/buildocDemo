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
@Table(name="archivos")
public class Archivo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name="pk_id_archivo")
    private Long idArchivo;
    @Column (name="arcNombre")
    private String nombreOriginal;
    @Column (name="arcFecha_creacion")
    private LocalDateTime fechaCreacion;
    @Column (name="arcTipo")
    private String tipo;
    @Column (name="arcTamaño")
    private String tamano;
    @Column (name="fk_id_usuario")
    private Long usuario_usuario;
    @Column (name="ruta")
    private String ruta;

    @ManyToOne
    @JoinColumn(name="fk_id_usuario", insertable = false,updatable = false)
    private Usuario usuario;
    @ManyToMany(mappedBy = "archivos", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TipoInspeccion> tipoInspecciones;
    @ManyToMany(mappedBy = "archivos", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Tarea> tareas;

}
