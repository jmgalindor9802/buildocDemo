package com.buildoc.buildocDemo.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name="pk_id_usuario")
    private Long id;
    @Column (name="password", length = 280)
    private String contraseña;
    @Column (name="email")
    private String email;

    @ManyToMany
    @JoinTable(name="usuarios_roles",joinColumns=@JoinColumn(name="fk_id_usuario"),
    inverseJoinColumns=@JoinColumn(name="fk_id_rol"))
    List<Rol> roles;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Equipo> equipos;


    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Incidente> incidentes;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Persona persona;

    @OneToOne(mappedBy = "lider", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Equipo liderDeEquipo;


}
