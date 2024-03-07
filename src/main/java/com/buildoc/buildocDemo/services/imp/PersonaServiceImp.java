package com.buildoc.buildocDemo.services.imp;

import com.buildoc.buildocDemo.entities.Persona;
import com.buildoc.buildocDemo.repositories.PersonaRepository;
import com.buildoc.buildocDemo.services.PersonaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PersonaServiceImp implements PersonaServices {
    @Autowired
    private PersonaRepository personaRepository;
    @Override
    public List<Persona> listarPersonas() {
        return personaRepository.findAll();
    }
    @Override
    public void crearPersona(Persona persona) {
        personaRepository.save(persona);
    }
    @Override
    public Persona obtenerPersonaPorId(Long id) {
        return personaRepository.getById(id);
    }
    @Override
    public void actualizarPersona(Persona persona) {
        personaRepository.save(persona);
    }
    @Override
    public void eliminarPersona(Persona persona) {
        personaRepository.delete(persona);
    }
}
