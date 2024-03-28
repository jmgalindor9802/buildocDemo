package com.buildoc.buildocDemo.entities;

import com.buildoc.buildocDemo.entities.enums.EstadoCiclo;
import lombok.*;
import javax.persistence.*;

import java.time.LocalDateTime;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Ciclo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long idCiclo;

    private String nombre;

    private LocalDateTime fechaCreacion;

    private String descripcion;
    @Enumerated(EnumType.STRING)

    private EstadoCiclo estado;


    @ManyToOne(cascade = CascadeType.ALL, fetch =FetchType.LAZY)
    private Proyecto proyecto;


}
