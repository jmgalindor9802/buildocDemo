package com.buildoc.buildocDemo.dto;

import lombok.Data;

import java.util.List;

@Data
public class LoginResponseDto {
    private String accessToken;
    private String nombre;
    private String username;
    private String rol;
    private Long idUsuario;

    public LoginResponseDto(String accessToken, String nombre, String username, String rol, Long idUsuario) {
        this.accessToken = accessToken;
        this.nombre = nombre;
        this.username = username;
        this.rol = rol;
        this.idUsuario=idUsuario;

    }
}
