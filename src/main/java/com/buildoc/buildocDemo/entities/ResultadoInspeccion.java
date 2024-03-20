package com.buildoc.buildocDemo.entities;

import com.buildoc.buildocDemo.entities.enums.EstadoResultadoInspeccion;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "resultadoInspecciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultadoInspeccion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pk_id_resultado_inspeccion", columnDefinition = "BIGINT(20)")
    private Long idResultadoInspeccion;

    @Column(name = "resDescripcion", nullable = false, length = 5000)
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(name = "resEstado", nullable = false)
    private EstadoResultadoInspeccion estado;

    @Column(name = "resAvanzeObra")
    private Integer avanceObra;

    @Column(name = "resObervacionesGenerales", nullable = false, length = 5000)
    private String observacionesGenerales;

    @Column(name = "fk_id_inspeccion", nullable = false)
    private Long idInspeccion;

    @Column(name="resFechaModificacion")
    private LocalDateTime fechaModificacion;

    @OneToOne
    private Inspeccion inspeccion;


}
