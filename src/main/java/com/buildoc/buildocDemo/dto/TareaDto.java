package com.buildoc.buildocDemo.dto;

import com.buildoc.buildocDemo.entities.Usuario;
import lombok.Data;

import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class TareaDto {
    private String nombre;
    private String proyecto;
    private String ciclo;
    private String descripcion;
    private LocalDateTime fechaInicial;

    private LocalDateTime fechaFinal;

    private List<Usuario> responsables;

}
