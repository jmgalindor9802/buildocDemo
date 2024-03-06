package com.buildoc.buildocDemo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pk_id_cliente")
    private Long id;
    @Column(name = "cliNombre")
    private String nombre;
    @Column(name = "cliCorreo")
    private String correo;
    @Column(name = "cliTelefono")
    private String telefono;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch =FetchType.LAZY, orphanRemoval = true)
    private List<Proyecto> proyectos;

}