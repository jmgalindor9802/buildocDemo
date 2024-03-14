package com.buildoc.buildocDemo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="equipos")
public class Equipo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pk_id_equipo")
    private Long id;
    @Column(name = "equNombre", length = 100 )
    private String nombre;
    @ManyToMany(mappedBy = "equipos")
    private List<Usuario> usuarios;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Proyecto proyecto;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Usuario lider;
}
