package com.buildoc.buildocDemo.dto;

import lombok.Data;

import java.util.Date;


@Data
public class PersonaDto {
    private String nombre;
    private String apellido;
    private String eps;
    private String arl;
    private Date fechaNacimiento;
    private String municipio;
    private String departamento;
    private String direccion;
    private String telefono;
    private String profesion;
}
