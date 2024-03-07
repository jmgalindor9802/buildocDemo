package com.buildoc.buildocDemo.controller;


import com.buildoc.buildocDemo.entities.Persona;
import com.buildoc.buildocDemo.services.PersonaServices;
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
    private PersonaServices personaServices;
    @PostMapping("create")
    public ResponseEntity<Map<String, Object>>create(@RequestBody Map<String,Object>request){
        Map<String,Object> response = new HashMap<>();
        try {
            System.out.println("@@@"+request);
            Persona persona= new Persona();
            //persona.setId(Long.parseLong(request.get(("id").toString())));
            persona.setCedula(1L);
            persona.setNombre(request.get("Nombre").toString());
            persona.setApellido(request.get("Apellido").toString());
            persona.setEps(request.get("EPS".toString()));
            persona.setArl("ARL");
            persona.setFechaNacimiento(new Date()); // Debes proporcionar una fecha válida aquí
            persona.setMunicipio("Municipio");
            persona.setDireccion("Dirección");
            persona.setProfesion("Profesión");
            persona.setTelefono(123456789L);
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