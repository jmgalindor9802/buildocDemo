package com.buildoc.buildocDemo.controller;


import com.buildoc.buildocDemo.dto.PersonaDto;
import com.buildoc.buildocDemo.entities.Persona;
import com.buildoc.buildocDemo.services.imp.PersonaServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequestMapping(path = "/buildoc/persona/",method = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.HEAD})
@CrossOrigin("*")
public class PersonaController {
    @Autowired
    private PersonaServiceImp personaServicesImp;
    @Transactional
    @PostMapping("create")
    public ResponseEntity<Map<String, Object>> create(@RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            Persona persona = new Persona();
            persona.setCedula(Long.parseLong(request.get("cedula").toString()));

            persona.setNombre(request.get("nombre").toString());
            persona.setApellido(request.get("apellido").toString());
            persona.setEps(request.get("eps").toString());
            persona.setArl(request.get("arl").toString());

            LocalDate parsedDateTime_fechaNacimiento = LocalDate.parse(request.get("fechaNacimiento").toString(), formatter);
            persona.setFechaNacimiento(parsedDateTime_fechaNacimiento);

            persona.setMunicipio(request.get("municipio").toString());
            persona.setDepartamento(request.get("departamento").toString());
            persona.setDireccion(request.get("direccion").toString());
            persona.setProfesion(request.get("profesion").toString());
            persona.setTelefono(request.get("telefono").toString());

            this.personaServicesImp.crearPersona(persona);
            response.put("status", "succes");
            response.put("data", "Registro exitoso");

        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_GATEWAY);
            response.put("data", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("all")
    public ResponseEntity<Map<String, Object>> findAll() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Persona> personasList = this.personaServicesImp.listarPersonas();
            response.put("status", "succes");
            response.put("data", personasList);

        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_GATEWAY);
            response.put("data", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Map<String, Object>> findbyId(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();

        try {
            Persona persona = personaServicesImp.obtenerPersonaPorId(id);

            if (persona == null) {
                response.put("status", HttpStatus.NOT_FOUND);
                response.put("data", "No se encontró ninguna persona con el ID proporcionado");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            PersonaDto personaDto= new PersonaDto();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String fechaNacimientoFormateada = persona.getFechaNacimiento().format(formatter);
            personaDto.setFechaNacimiento(fechaNacimientoFormateada);


            personaDto.setNombre(persona.getNombre());
            personaDto.setApellido(persona.getApellido());
            personaDto.setEps(persona.getEps());
            personaDto.setArl(persona.getArl());
            personaDto.setMunicipio(persona.getMunicipio());
            personaDto.setDepartamento(persona.getDepartamento());
            personaDto.setDireccion(persona.getDireccion());
            personaDto.setProfesion(persona.getProfesion());
            personaDto.setTelefono(persona.getTelefono());


            response.put("status", HttpStatus.OK);
            response.put("data", personaDto);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
            response.put("data", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("delete/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            // Buscar la persona por su ID
            Persona persona = personaServicesImp.obtenerPersonaPorId(id);
            if (persona == null) {
                response.put("status", HttpStatus.NOT_FOUND);
                response.put("data", "No se encontró ninguna persona con el ID proporcionado");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            // Eliminar la persona de la base de datos
            personaServicesImp.eliminarPersona(persona);

            response.put("status", HttpStatus.OK);
            response.put("data", "Persona eliminada exitosamente");
        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_REQUEST);
            response.put("data", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PutMapping("update/{id}")
    public ResponseEntity<Map<String, Object>> updateById(@PathVariable Long id, @RequestBody PersonaDto personaDto) {
        Map<String, Object> response = new HashMap<>();

        try {
            Persona persona = personaServicesImp.obtenerPersonaPorId(id);

            if (persona == null) {
                response.put("status", HttpStatus.NOT_FOUND);
                response.put("data", "No se encontró ninguna persona con el ID proporcionado");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            // Actualizar los datos de la persona con los datos recibidos del frontend

            persona.setNombre(personaDto.getNombre());
            persona.setApellido(personaDto.getApellido());
            persona.setEps(personaDto.getEps());
            persona.setArl(personaDto.getArl());
            persona.setMunicipio(personaDto.getMunicipio());
            persona.setDepartamento(personaDto.getDepartamento());
            persona.setDireccion(personaDto.getDireccion());
            persona.setProfesion(personaDto.getProfesion());
            persona.setTelefono(personaDto.getTelefono());

            // Guardar los cambios en la base de datos
            personaServicesImp.actualizarPersona(persona);

            response.put("status", HttpStatus.OK);
            response.put("data", "Persona actualizada correctamente");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
            response.put("data", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}