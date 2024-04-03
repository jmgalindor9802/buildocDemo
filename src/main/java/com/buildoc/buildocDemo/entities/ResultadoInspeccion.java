package com.buildoc.buildocDemo.entities;

import com.buildoc.buildocDemo.entities.enums.EstadoResultadoInspeccion;
import lombok.*;
import javax.persistence.*;

import java.time.LocalDateTime;

@Entity

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
public class ResultadoInspeccion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long idResultadoInspeccion;
    private String descripcion;
    @Enumerated(EnumType.STRING)
    private EstadoResultadoInspeccion estado;
    private Integer avanceObra;
    private String observacionesGenerales;
    private Long idInspeccion;
    private LocalDateTime fechaModificacion;

    @OneToOne
    private Inspeccion inspeccion;


}
