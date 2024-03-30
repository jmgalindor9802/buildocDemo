package com.buildoc.buildocDemo.entities;

import lombok.*;
import javax.persistence.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table

public class Equipo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;

    private String nombre;

    @ManyToMany (cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Usuario> usuarios;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Proyecto proyecto;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Usuario lider;
}
