package com.buildoc.buildocDemo.entities;

import lombok.*;
import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long idComentario;

    private String descripcion;

    private Long autor;

    private Long tarea_comentario;

    @ManyToOne
    private Tarea tarea;
}
