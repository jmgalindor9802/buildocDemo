package com.buildoc.buildocDemo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "personas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Persona {
    @Id
    @Column(name = "pk_id_persona", columnDefinition = "BIGINT(20)")
    private Long cedula;
    @Column(name = "perNombre", nullable = false, length = 280)
    private String nombre;
    @Column(name = "perApellido", nullable = false, length = 280)
    private String apellido;
    @Column (name="perNombre_eps", nullable = false, length = 280)
    private String eps;
    @Column (name="perNombre_arl", nullable = false, length = 280)
    private String arl;
    @Column(name="perFecha_nacimiento", nullable = false)
    private Date fechaNacimiento;
    @Column(name="perMunicipio", nullable = false, length = 280)
    private String municipio;
    @Column(name="perDireccion_residencia", nullable = false, length = 280)
    private String direccion;
    @Column (name="perProfesion", nullable = false, length = 280)
    private String profesion;
    @Column (name="perTelefono", nullable = false)
    private String Telefono;


}
