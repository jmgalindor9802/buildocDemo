package com.buildoc.buildocDemo.entities;

import com.buildoc.buildocDemo.entities.enums.EstadoDato;
import lombok.*;
import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
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
    @Enumerated(EnumType.STRING)
    private EstadoDato estadoDato;
    @ManyToOne
    private Cliente cliente;
    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Incidente> incidentes;

}
