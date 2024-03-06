package com.buildoc.buildocDemo.entities;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "involucrados")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvolucradoIncidente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pk_id_involucrado", columnDefinition = "BIGINT(20)")
    private Long idInvolucrado;
    @Column(name = "invRelacionIncidente", nullable = false, length = 200)
    private String relacionIncidente;
    @Column(name = "fk_id_persona", nullable = false)
    private Long idPersona;
    @Column(name = "fk_id_incidente", nullable = false)
    private Long idIncidente;
    @ManyToOne
    @JoinColumn(name = "fk_id_incidente",insertable = false,updatable = false)
    private Incidente incidente;
}
