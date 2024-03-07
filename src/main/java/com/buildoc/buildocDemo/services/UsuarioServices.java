package com.buildoc.buildocDemo.services;

import com.buildoc.buildocDemo.entities.Persona;
import com.buildoc.buildocDemo.entities.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UsuarioServices {
    public List<Persona> listarPersonas();

    Persona crearPersona(Persona persona);

    Persona obtenerPersonaPorId(Long id);

    Persona actualizarPersona(Persona persona);

    void eliminarEquipo(Long id);
}