package com.buildoc.buildocDemo.controller;


import com.buildoc.buildocDemo.entities.Persona;

import com.buildoc.buildocDemo.services.imp.PersonaServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
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
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Persona persona = new Persona();
            persona.setCedula(Long.parseLong(request.get("cedula").toString()));
            Date parsedDate = formatter.parse(request.get("fechaNacimiento").toString());
            persona.setNombre(request.get("nombre").toString());
            persona.setApellido(request.get("apellido").toString());
            persona.setEps(request.get("eps").toString());
            persona.setArl(request.get("arl").toString());
            persona.setFechaNacimiento(parsedDate);
            persona.setMunicipio(request.get("municipio").toString());
            persona.setDireccion(request.get("direccion").toString());
            persona.setProfesion(request.get("profesion").toString());
            persona.setTelefono(request.get("telefono").toString());

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