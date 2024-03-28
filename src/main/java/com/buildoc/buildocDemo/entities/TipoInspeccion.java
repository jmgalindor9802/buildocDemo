package com.buildoc.buildocDemo.entities;

import lombok.*;
import javax.persistence.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TipoInspeccion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long idTipoInspeccion;

    private String nombre;

    private String descripcion;

    @OneToMany(cascade = CascadeType.ALL, fetch =FetchType.LAZY, orphanRemoval = true)
    private List<Inspeccion> inspecciones;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Archivo> archivos;

}
