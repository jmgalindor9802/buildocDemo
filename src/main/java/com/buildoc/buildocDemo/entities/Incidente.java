package com.buildoc.buildocDemo.entities;

import com.buildoc.buildocDemo.entities.enums.IncidenteEstado;
import com.buildoc.buildocDemo.entities.enums.IncidenteGravedad;
import lombok.*;
import javax.persistence.*;
import java.sql.Date; /*Esta clase se utiliza para representar fechas sin hora en Java, similar altipo de dato
DATE en SQL.*/
import java.time.LocalDateTime;
import java.util.List;

@Entity

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Incidente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long idIncidente;


    private String nombre;


    private String descripcion;

    /*Esta anotación se utiliza junto a la definición de un enum para indicar cómo se almacenará el valor del
    enum en la base de datos. En este caso, EnumType.STRING indica que se almacenará el nombre del valor del
    enum como una cadena de texto.*/
    @Enumerated(EnumType.STRING)

    private IncidenteEstado estado;

    /*Esta anotación se utiliza junto a la definición de un enum para indicar cómo se almacenará el valor del
    enum en la base de datos. En este caso, EnumType.STRING indica que se almacenará el nombre del valor del
    enum como una cadena de texto.*/
    @Enumerated(EnumType.STRING)

    private IncidenteGravedad gravedad;


    private LocalDateTime fecha;


    private String sugerencias;


    private Long idUsuario;


    private Long idProyecto;
    @ManyToOne

    private Proyecto proyecto;
    @ManyToOne

    private Usuario usuario;
}
