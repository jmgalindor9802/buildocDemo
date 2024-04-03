package com.buildoc.buildocDemo.dto;

import com.buildoc.buildocDemo.entities.enums.EstadoRespuestaTarea;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class RespuestaTareaDto {
    private Long id;
    private LocalDateTime fechaEntrega;
    private EstadoRespuestaTarea estado;
    private String comentario;
    private Long idTarea;
    private Long idUsuario;
}
