package com.buildoc.buildocDemo.controller;

import com.buildoc.buildocDemo.entities.Ciclo;
import com.buildoc.buildocDemo.entities.InvolucradoIncidente;
import com.buildoc.buildocDemo.services.imp.InvolucradoIncidenteServiceImp;
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
    private InvolucradoIncidenteServiceImp incidenteServiceImp;
    @PostMapping
    public ResponseEntity<Map<String, Object>> create(@RequestBody Map<String,Object>request){
        Map<String, Object> response = new HashMap<>();
        try {
            InvolucradoIncidente involucradoIncidente = new InvolucradoIncidente();
            involucradoIncidente.setRelacionIncidente(request.get("relacionIncidente").toString());
            this.incidenteServiceImp.crearInvolucradoIncidente(involucradoIncidente);
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
            List<InvolucradoIncidente> involucradoIncidenteList = this.incidenteServiceImp.listarInvolucradosIncidentes();
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
