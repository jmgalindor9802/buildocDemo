package com.buildoc.buildocDemo.dto;

import lombok.Data;

import java.util.List;

@Data
public class LoginResponseDto {
    private String accessToken;
    private String nombre;
    private String username;
    private List<String> roles;

    public LoginResponseDto(String accessToken, String nombre, String username, List<String> roles) {
        this.accessToken = accessToken;
        this.nombre = nombre;
        this.username = username;
        this.roles = roles;
    }
}
