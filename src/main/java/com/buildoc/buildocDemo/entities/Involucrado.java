package com.buildoc.buildocDemo.entities;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "involucrados")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Involucrado {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pk_id_involucrado", columnDefinition = "BIGINT(20)")
    private Long idInvolucrado;

    @Column(name = "invNombre", nullable = false, length = 280)
    private String nombre;

    @Column(name = "invApellido", nullable = false, length = 280)
    private String apellido;

    @Column(name = "invNumDocumento", nullable = false)
    private Long numDocumento;

    @Column(name = "invJustificacion", nullable = false, length = 200)
    private String justificacion;

    @Column(name = "fk_id_incidente", nullable = false)
    private Long idIncidente;
}
