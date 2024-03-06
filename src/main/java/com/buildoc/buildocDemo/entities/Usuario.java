package com.buildoc.buildocDemo.entities;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name="pk_id_usuario")
    private Long id;
    @Column (name="usuNombre")
    private String nombre;
    @Column (name="usuApellido")
    private String apellido;
    @Column (name="usuNombre_eps")
    private String eps;
    @Column (name="usuNombre_alr")
    private String arl;
    @Column(name="usuFecha_nacimiento")
    private Date fechaNacimiento;
    @Column(name="usuMunicipio")
    private String municipio;
    @Column(name="usuDireccion_residencia")
    private String direccion;
    @Column (name="usuProfesion")
    private String profesion;
    @Column (name="usuTelefono")
    private String telefono;
    @Column (name="password");
    private String password;
    @Column (name="email")
    private String email;

    @OnetoMany(mappedBy="usuario",cascade=CascadeType.All, fetch = FetchType.LAZY, orphanRemoval=true)
    private List<Proyecto> proyectos;

    @ManytoMany
    @JoinTable(name="usuarios_roles",joinColumns=@JoinColumn(name="fk_id_usuario"),
    inverseJoinsColumns=@JoinColumn(name="fk_id_rol"))
    List<Rol> roles;
    @ManyToMany
    @JoinTable(name = "usuarios_equipos", joinColumns = @JoinColumn(name = "fk_id_usuario"),
            inverseJoinColumns =@JoinColumn(name = "fk_id_equipo"))
    private List<Equipo> equipos;



}
