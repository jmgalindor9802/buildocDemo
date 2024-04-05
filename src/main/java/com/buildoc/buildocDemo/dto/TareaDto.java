package com.buildoc.buildocDemo.dto;

import com.buildoc.buildocDemo.entities.Usuario;
import lombok.Data;

import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.spi.DateFormatProvider;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
public class TareaDto {


    private String nombre;
    private String proyecto;
    private String ciclo;
    private String descripcion;
    private String fechaInicial;
    private String fechaFinal;
    private String responsables;

}
