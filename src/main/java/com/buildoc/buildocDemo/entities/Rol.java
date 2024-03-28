package com.buildoc.buildocDemo.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@Entity
@Table (name="roles")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Rol {
    @Id
    private int id;
    private String nombre;

    @ManyToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Usuario> usuarios;
}
