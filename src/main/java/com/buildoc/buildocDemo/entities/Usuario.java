package com.buildoc.buildocDemo.entities;


import com.buildoc.buildocDemo.entities.enums.EstadoDato;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    private String password;
    private String username;
    private String nombre;
    @Enumerated(EnumType.STRING)
    private EstadoDato estadoDato;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Rol> roles= new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Equipo> equipos;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Archivo> archivos;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Incidente> incidentes;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Persona persona;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Equipo> liderDeEquipos;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario")
    private List<RespuestaTarea> respuestasTarea;


}
