package com.buildoc.buildocDemo.services.imp;

import com.buildoc.buildocDemo.entities.Persona;
import com.buildoc.buildocDemo.services.UsuarioServices;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImp implements UsuarioServices {
    @Override
    public List<Persona> listarPersonas() {
        return null;
    }

    @Override
    public Persona crearPersona(Persona persona) {
        return null;
    }

    @Override
    public Persona obtenerPersonaPorId(Long id) {
        return null;
    }

    @Override
    public Persona actualizarPersona(Persona persona) {
        return null;
    }

    @Override
    public void eliminarEquipo(Long id) {

    }
}
