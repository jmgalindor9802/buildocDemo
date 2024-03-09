package com.buildoc.buildocDemo.controller;


import com.buildoc.buildocDemo.entities.Persona;

import com.buildoc.buildocDemo.services.imp.PersonaServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping(path = "/api/persona/",method = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.HEAD})
@CrossOrigin("*")
public class PersonaController {
    @Autowired
    private PersonaServiceImp personaServices;
    @PostMapping("create")
    public ResponseEntity<Map<String, Object>> create(@RequestBody Map<String,Object>request){
        Map<String,Object> response = new HashMap<>();
        try {
            Persona persona = new Persona();
            persona.setCedula(Long.parseLong((String) request.get("cedula")));
            persona.setNombre((String) request.get("nombre"));
            persona.setApellido((String) request.get("apellido"));
            persona.setEps((String) request.get("eps"));
            persona.setArl((String) request.get("arl"));
            persona.setFechaNacimiento((Date) request.get("fechaNacimiento"));
            persona.setMunicipio((String) request.get("municipio"));
            persona.setDireccion((String) request.get("direccion"));
            persona.setProfesion((String) request.get("profesion"));
            persona.setTelefono((String) request.get("telefono"));

            this.personaServices.crearPersona(persona);
            response.put("status","succes");
            response.put("data","Registro exitoso");

        }catch (Exception e){
            response.put("status",HttpStatus.BAD_GATEWAY);
            response.put("data",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}