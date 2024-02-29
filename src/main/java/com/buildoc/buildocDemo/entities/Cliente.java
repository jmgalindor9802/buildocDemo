package com.buildoc.buildocDemo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cliente {
    @Column(name = "pk_id_cliente")
    private Long id;
    @Column(name = "cliNombre")
    private String nombre;
    @Column(name = "cliCorreo")
    private String correo;
    @Column(name = "cliTelefono")
    private String telefono;

}