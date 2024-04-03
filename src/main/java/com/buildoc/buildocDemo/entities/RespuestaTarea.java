package com.buildoc.buildocDemo.entities;

import com.buildoc.buildocDemo.entities.enums.EstadoRespuestaTarea;
import com.buildoc.buildocDemo.entities.enums.EstadoResultadoInspeccion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
@Entity
public class RespuestaTarea {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idRespuestaTarea;
    private String comentario;
    @Enumerated(EnumType.STRING)
    private EstadoRespuestaTarea estado;
    private LocalDateTime fechaEntrega;


    @ManyToOne
    private Tarea tarea;

    @ManyToOne
    private Usuario usuario;
}
