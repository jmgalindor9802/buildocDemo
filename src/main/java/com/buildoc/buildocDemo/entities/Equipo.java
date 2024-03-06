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
    @Column(name = "fk_id_usuario")
    private Long idLider;
    @Column(name = "fk_id_proyecto")
    @ManyToMany(mappedBy = "equipos")
    private List<Usuario> usuarios;
    @ManyToOne
    @JoinColumn(name="fk_id_proyecto", insertable = false, updatable = false)
    private Proyecto proyecto;
}
