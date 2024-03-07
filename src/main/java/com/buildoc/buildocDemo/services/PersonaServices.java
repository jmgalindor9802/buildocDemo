package com.buildoc.buildocDemo.services;

import com.buildoc.buildocDemo.entities.Archivo;
import com.buildoc.buildocDemo.entities.Persona;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface PersonaServices {
    public List<Persona> listarPersonas();

    public void crearPersona(Persona persona);

    public Persona obtenerPersonaPorId(Long id);

    public void actualizarPersona(Persona persona);

 public void eliminarPersona(Persona persona);

}

