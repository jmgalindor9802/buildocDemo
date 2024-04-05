package com.buildoc.buildocDemo.dto;

import lombok.Data;

@Data
public class IncidenteDto {
    private String proyecto;
    private String nombre;
    private String descripcion;
    private String fechaReporte;
}
