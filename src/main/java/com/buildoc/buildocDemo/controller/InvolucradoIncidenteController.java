package com.buildoc.buildocDemo.controller;

import com.buildoc.buildocDemo.entities.Ciclo;
import com.buildoc.buildocDemo.entities.Incidente;
import com.buildoc.buildocDemo.entities.InvolucradoIncidente;
import com.buildoc.buildocDemo.entities.Persona;
import com.buildoc.buildocDemo.services.imp.IncidenteServiceImp;
import com.buildoc.buildocDemo.services.imp.InvolucradoIncidenteServiceImp;
import com.buildoc.buildocDemo.services.imp.PersonaServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/involucrado-Incidente/",method = {RequestMethod.GET,RequestMethod.POST,
        RequestMethod.PUT,RequestMethod.HEAD})
@CrossOrigin("*")
public class InvolucradoIncidenteController {
    @Autowired
    private InvolucradoIncidenteServiceImp involucradoIncidenteServiceImp;
    @Autowired
    private PersonaServiceImp personaServiceImp;
    @Autowired
    private IncidenteServiceImp incidenteServiceImp;
    @PostMapping
    public ResponseEntity<Map<String, Object>> create(@RequestBody Map<String,Object>request){
        Map<String, Object> response = new HashMap<>();
        try {
            InvolucradoIncidente involucradoIncidente = new InvolucradoIncidente();
            Long idPersona = Long.parseLong(request.get("idPersona").toString());
            Persona persona = personaServiceImp.obtenerPersonaPorId(idPersona);
            if (persona == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            involucradoIncidente.setPersona(persona);
            involucradoIncidente.setRelacionIncidente(request.get("relacionIncidente").toString());

            Long idIincidente = Long.parseLong(request.get("idIncidente").toString());
            Incidente incidente = incidenteServiceImp.obtenerIncidentePorId(idIincidente);
            if (incidente == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            involucradoIncidente.setIncidente(incidente);

            this.involucradoIncidenteServiceImp.crearInvolucradoIncidente(involucradoIncidente);
            response.put("status","succes");
            response.put("data","Registro exitoso");

        }catch (Exception e){
            response.put("status", HttpStatus.BAD_GATEWAY);
            response.put("data",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("all")
    public ResponseEntity<Map<String, Object>> findAll(){
        Map<String,Object> response = new HashMap<>();
        try {
            List<InvolucradoIncidente> involucradoIncidenteList =
                    this.involucradoIncidenteServiceImp.listarInvolucradosIncidentes();
            response.put("status","succes");
            response.put("data", involucradoIncidenteList);

        }catch (Exception e){
            response.put("status",HttpStatus.BAD_GATEWAY);
            response.put("data",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
