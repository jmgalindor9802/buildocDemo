package com.buildoc.buildocDemo.entities;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date; /*Esta clase se utiliza para representar fechas sin hora en Java, similar altipo de dato
DATE en SQL.*/
import java.util.List;

@Entity
@Table(name = "incidentes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Incidente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pk_id_incidente", columnDefinition = "BIGINT(20)")
    private Long idIncidente;

    @Column(name = "incNombre", nullable = false, length = 280)
    private String nombre;

    @Column(name = "incDescripcion", nullable = false, length = 5000)
    private String descripcion;

    /*Esta anotación se utiliza junto a la definición de un enum para indicar cómo se almacenará el valor del
    enum en la base de datos. En este caso, EnumType.STRING indica que se almacenará el nombre del valor del
    enum como una cadena de texto.*/
    @Enumerated(EnumType.STRING)
    @Column(name = "incEstado", nullable = false, length = 20)
    private IncidenteEstado estado;

    /*Esta anotación se utiliza junto a la definición de un enum para indicar cómo se almacenará el valor del
    enum en la base de datos. En este caso, EnumType.STRING indica que se almacenará el nombre del valor del
    enum como una cadena de texto.*/
    @Enumerated(EnumType.STRING)
    @Column(name = "incGravedad", nullable = false, length = 10)
    private IncidenteGravedad gravedad;

    @Column(name = "incFecha", nullable = false, updatable = false)
    private String fecha;

    @Column(name = "incSugerencias", length = 5000)
    private String sugerencias;

    @Column(name = "fk_id_usuario", nullable = false)
    private Long idUsuario;

    @Column(name = "fk_id_proyecto", nullable = false)
    private Long idProyecto;

    /*Es un tipo de dato enum personalizado que define dos constantes: INICIALIZADO y FINALIZADO. Estas
    constantes representan los posibles estados de un incidente.*/
    public enum IncidenteEstado {
        INICIALIZADO,
        FINALIZADO
    }

    /*Es un tipo de dato enum personalizado que define tres constantes: ALTO, MEDIO y BAJO. Estas
    constantes representan los posibles estados de un incidente.*/
    public enum IncidenteGravedad {
        ALTO,
        MEDIO,
        BAJO
    }

    @OneToMany(mappedBy = "incidente",cascade = CascadeType.ALL, fetch =FetchType.LAZY)
    private List<Seguimiento> seguimientos;
    @OneToMany(mappedBy = "incidente",cascade = CascadeType.ALL, fetch =FetchType.LAZY)
    private List<InvolucradoIncidente> involucradoIncidentes;
    @ManyToOne
    @JoinColumn(name="fk_id_proyecto",insertable = false,updatable = false)
    private Proyecto proyecto;
    @ManyToOne
    @JoinColumn(name="fk_id_usuario",insertable = false,updatable = false)
    private Usuario usuario;
}
