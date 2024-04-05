package com.buildoc.buildocDemo.dto;

import lombok.Data;

import java.util.List;

@Data
public class UsuarioDto {
    private String username;
    private String password;
    private String nombre;
    private String rol;
    private Long personaId;
}
