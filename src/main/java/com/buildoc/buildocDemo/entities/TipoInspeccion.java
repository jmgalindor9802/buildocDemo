package com.buildoc.buildocDemo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="tipoInspecciones")
public class TipoInspeccion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="pk_id_tipoInspeccion")
    private Long idTipoInspeccion;
    @Column(name = "tpiNombre", length = 100)
    private String nombre;
    @Column(name = "tpiDescripcion", length = 5000)
    private String descripcion;

    @OneToMany(mappedBy = "tipoInspeccion", cascade = CascadeType.ALL, fetch =FetchType.LAZY, orphanRemoval = true)
    private List<Inspeccion> inspecciones;
    @ManyToMany
    @JoinTable(name = "tipoInspecciones_archivos", joinColumns = @JoinColumn(name = "fk_id_tipoInspeccion"),
            inverseJoinColumns = @JoinColumn(name = "fk_id_archivo"))
    private List<Archivo> archivos;


}
