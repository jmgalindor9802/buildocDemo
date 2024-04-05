package com.buildoc.buildocDemo.entities;

import com.buildoc.buildocDemo.entities.enums.EstadoDato;
import lombok.*;
import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table
public class TipoInspeccion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long idTipoInspeccion;
    private String nombre;
    private String descripcion;
    private EstadoDato estadoDato;
    private LocalDateTime fechaCreacion;

    @OneToMany(cascade = CascadeType.ALL, fetch =FetchType.LAZY, orphanRemoval = true)
    private List<Inspeccion> inspecciones;

    @ManyToMany( fetch = FetchType.LAZY)
    private List<Archivo> archivos;

}
