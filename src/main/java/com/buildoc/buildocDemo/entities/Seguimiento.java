package com.buildoc.buildocDemo.entities;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date; /*Esta clase se utiliza para representar fechas sin hora en Java, similar altipo de dato
DATE en SQL.*/

@Entity
@Table(name = "seguimientos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Seguimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pk_id_seguimiento", columnDefinition = "BIGINT(20)")
    private Long idSeguimiento;

    @Column(name = "actDescripcion", nullable = false, length = 5000)
    private String descripcion;

    @Column(name = "actFecha", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    @Column(name = "actSugerencia", nullable = false, length = 5000)
    private String sugerencia;

    @Column(name = "fk_id_incidente", nullable = false)
    private Long idIncidente;
    @ManyToOne
    @JoinColumn(name="fk_id_incidente",insertable = false,updatable = false)
    private Incidente incidente;
}
