package com.buildoc.buildocDemo.entities;

import com.buildoc.buildocDemo.entities.enums.EstadoCiclo;
import com.buildoc.buildocDemo.entities.enums.EstadoDato;
import lombok.*;
import javax.persistence.*;

import java.time.LocalDateTime;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table
public class Ciclo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long idCiclo;

    private String nombre;

    private LocalDateTime fechaCreacion;

    private String descripcion;
    private EstadoDato estadoDato;
    @Enumerated(EnumType.STRING)

    private EstadoCiclo estado;


    @ManyToOne(cascade = CascadeType.ALL, fetch =FetchType.EAGER)
    private Proyecto proyecto;


}
