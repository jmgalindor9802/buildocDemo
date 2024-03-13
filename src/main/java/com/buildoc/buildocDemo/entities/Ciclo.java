package com.buildoc.buildocDemo.entities;

import com.buildoc.buildocDemo.entities.enums.EstadoCiclo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table (name="ciclos")
public class Ciclo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name="pk_id_ciclo")
    private Long idCiclo;
    @Column(name="cicNombre")
    private String nombre;
    @Column (name="cicFecha_creacion")
    private LocalDateTime fechaCreacion;
    @Column (name="cicDescripcion")
    private String descripcion;
    @Enumerated(EnumType.STRING)
    @Column (name="cicEstado")
    private EstadoCiclo estado;


    @ManyToOne
    @JoinColumn(name="idProyecto", insertable = false,updatable = false)
    private Proyecto proyecto;


}
