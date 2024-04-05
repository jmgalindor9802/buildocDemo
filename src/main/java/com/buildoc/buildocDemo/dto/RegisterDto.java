package com.buildoc.buildocDemo.dto;

import lombok.Data;

import java.util.List;

@Data
public class RegisterDto {
    private String username;
    private String password;
    private String nombre;
    private Long rol;
    private Long personaId;

}
