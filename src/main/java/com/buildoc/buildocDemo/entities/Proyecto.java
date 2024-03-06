package com.buildoc.buildocDemo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Proyecto {

    @Column (name="pk_id_proyecto")
    private Long id;
    @Column (name="proNombre")
    private String nombre;
    @Column (name="proMunicipio")
    private String municipio;
    @Column (name="proDireccion")
    private String direccion;
    @Column (name="proDescripcion", length = 5000)
    private String descripcion;
    @Column (name="proFecha_creacion")
    private LocalDateTime fechacreacion;
    @Column(name = "fk_id_cliente")
    private Long idCliente;
    @ManyToOne
    @JoinColumn(name="fk_id_cliente", insertable = false,updatable = false)
    private Cliente cliente;

    @OneToMany(mappedBy = "proyecto", cascade = CascadeType.ALL,fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Ciclo> ciclos;

    @OneToMany(mappedBy = "proyectos", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Equipo> equipos;


}
