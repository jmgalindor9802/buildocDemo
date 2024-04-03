package com.buildoc.buildocDemo.entities;

import com.buildoc.buildocDemo.entities.enums.EstadoDato;
import lombok.*;
import javax.persistence.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private String nombre;
    private String correo;
    private String telefono;
    @Enumerated(EnumType.STRING)
    private EstadoDato estadoDato;

    @OneToMany
    private List<Proyecto> proyectos;

}