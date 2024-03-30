package com.buildoc.buildocDemo.entities;

import lombok.*;
import javax.persistence.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
public class InvolucradoIncidente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pk_id_involucrado", columnDefinition = "BIGINT(20)")
    private Long idInvolucrado;
    @Column(name = "invRelacionIncidente", nullable = false, length = 200)
    private String relacionIncidente;
    @ManyToOne(cascade = CascadeType.ALL, fetch =FetchType.LAZY)
    private Incidente incidente;

    @OneToOne
    private Persona persona;
}
