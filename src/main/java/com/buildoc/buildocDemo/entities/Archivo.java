package com.buildoc.buildocDemo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="archivos")
public class Archivo {
    @Column (name="pk_id_archivo")
    private Long idArchivo;
    @Column (name="arcNombre_Original")
    private String nombreOriginal;
    @Column (name="arcFecha_creacion")
    private LocalDateTime fechaCreacion;
    @Column (name="arcTipo")
    private String tipo;
    @Column (name="arcTamaño")
    private String tamano;
    @Column(name ="version")
    @Column (name="fk_id_usuario")
    private Long usuario;
    @Column (name="fk_id_carpeta")
    private Long carpeta;

    @ManyToOne
    @JoinColumn(name="fk_id_usuario", insertable = false,updatable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name="fk_id_carpeta", insertable = false,updatable = false)
    private Carpeta carpeta;





}
