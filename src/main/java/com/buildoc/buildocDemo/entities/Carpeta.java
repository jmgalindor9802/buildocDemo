package com.buildoc.buildocDemo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date; /*Esta clase se utiliza para representar fechas sin hora en Java, similar altipo de dato
DATE en SQL.*/
@Entity
@Table(name = "carpetas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Carpeta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pk_id_carpeta", columnDefinition = "BIGINT(20)")
    private Long idCarpeta;

    /*La propiedad nullable = false en java indica que un tipo de referencia no puede contener un valor null*/
    @Column(name = "carNombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "carEtiqueta", nullable = false, length = 20)
    private String etiqueta;

    /*La propiedad updatable = false en JPA indica que un campo o columna en una entidad no se puede
    actualizar después de que se ha insertado en la base de datos. */
    @Column(name = "carFecha_creacion", nullable = false, updatable = false)
    /*Esta anotación se utiliza junto con la anotación @Entity para mapear la propiedad a una columna de tipo
    TIMESTAMP en la base de datos.*/
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;

    @Column(name = "carDescripcion", length = 5000)
    private String descripcion;

    @Column(name = "fk_id_usuario", nullable = false)
    private Long idUsuario;

    @Column(name = "fk_id_proyecto", nullable = false)
    private Long idProyecto;
}
