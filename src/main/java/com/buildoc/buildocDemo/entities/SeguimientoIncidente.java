package com.buildoc.buildocDemo.entities;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "seguimientos")
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
