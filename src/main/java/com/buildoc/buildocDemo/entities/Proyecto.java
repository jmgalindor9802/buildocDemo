package com.buildoc.buildocDemo.entities;

import lombok.*;
import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Proyecto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nombre;

    private String municipio;

    private String direccion;

    private String descripcion;

    private LocalDateTime fechacreacion;

    private LocalDateTime fechaModificacion;
    @ManyToOne
    private Cliente cliente;
    @OneToMany(mappedBy = "proyecto", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Incidente> incidentes;

}
