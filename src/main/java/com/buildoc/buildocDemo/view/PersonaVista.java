package com.buildoc.buildocDemo.view;

import java.util.Date; // Agrega esta importación

import com.buildoc.buildocDemo.entities.Persona;
import com.buildoc.buildocDemo.repositories.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class PersonaVista implements CommandLineRunner {
    @Autowired
    private PersonaRepository personaRepository;
    @Override
    public void run(String... args) throws Exception {
        Persona persona1 = new Persona();
        persona1.setCedula(1L);
        persona1.setNombre("Nombre");
        persona1.setApellido("Apellido");
        persona1.setEps("EPS");
        persona1.setArl("ARL");
        persona1.setFechaNacimiento(new Date()); // Debes proporcionar una fecha válida aquí
        persona1.setMunicipio("Municipio");
        persona1.setDireccion("Dirección");
        persona1.setProfesion("Profesión");
        persona1.setTelefono(123456789L);

        // Guardar la instancia de Persona en la base de datos
        personaRepository.save(persona1);
    }
}
