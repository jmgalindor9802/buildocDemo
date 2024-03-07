package com.buildoc.buildocDemo.entities;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;
import lombok.Data;
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
    @Column (name="password", length = 280)
    private String contraseña;
    @Column (name="email")
    private String email;

    @ManyToMany
    @JoinTable(name="usuarios_roles",joinColumns=@JoinColumn(name="fk_id_usuario"),
    inverseJoinColumns=@JoinColumn(name="fk_id_rol"))
    List<Rol> roles;
    @ManyToMany
    @JoinTable(name = "usuarios_equipos", joinColumns = @JoinColumn(name = "fk_id_usuario"),
            inverseJoinColumns =@JoinColumn(name = "fk_id_equipo"))
    private List<Equipo> equipos;
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Incidente> incidentes;
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Persona persona;

}
