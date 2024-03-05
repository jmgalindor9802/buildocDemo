package com.buildoc.buildocDemo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pk_id_rol", columnDefinition = "BIGINT(20)")
    private long idRol;
    @Column(name = "rolNombre", length = 100)
    private String nombre;

    @ManytoMany(mappedBy="roles")
    List<Usuario> usuarios;
}
