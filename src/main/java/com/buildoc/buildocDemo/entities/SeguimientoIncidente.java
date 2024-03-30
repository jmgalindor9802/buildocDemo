package com.buildoc.buildocDemo.entities;

import lombok.*;
import javax.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeguimientoIncidente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pk_id_seguimiento", columnDefinition = "BIGINT(20)")
    private Long idSeguimiento;

    @Column(name = "actDescripcion", nullable = false, length = 5000)
    private String descripcion;

    @Column(name = "actFecha", nullable = false)
    private LocalDateTime fecha;

    @Column(name = "actSugerencia", nullable = false, length = 5000)
    private String sugerencia;

    @ManyToOne
    private Incidente incidente;
}
