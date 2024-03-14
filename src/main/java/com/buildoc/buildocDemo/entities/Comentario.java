package com.buildoc.buildocDemo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="comentarios")
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="pk_id_comentario")
    private Long idComentario;
    @Column(name = "comDescripcion")
    private String descripcion;
    @Column(name = "fk_id_usuario")
    private Long autor;
    @Column(name = "fk_id_tarea")
    private Long tarea_comentario;

    @ManyToOne
    private Tarea tarea;
}
