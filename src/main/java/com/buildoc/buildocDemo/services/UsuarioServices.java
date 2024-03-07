package com.buildoc.buildocDemo.services;

import com.buildoc.buildocDemo.entities.Persona;
import com.buildoc.buildocDemo.entities.Usuario;

import java.util.List;

public interface UsuarioServices {
    public List<Persona> listarPersonas();

    Persona crearPersona(Persona persona);

    Persona obtenerPersonaPorId(Long id);

    Persona actualizarPersona(Persona persona);

    void eliminarEquipo(Long id);
}
