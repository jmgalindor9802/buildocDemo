package com.buildoc.buildocDemo.controller;

import com.buildoc.buildocDemo.entities.Incidente;
import com.buildoc.buildocDemo.entities.Rol;
import com.buildoc.buildocDemo.entities.SeguimientoIncidente;
import com.buildoc.buildocDemo.services.imp.SeguimientoIncidenteServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/seguimientoincidente/",method = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.HEAD})
@CrossOrigin("*")
public class SeguimientoIncidenteController {
    @Autowired
    private SeguimientoIncidenteServiceImp seguimientoIncidenteServiceImp;

    @PostMapping("create")
    public ResponseEntity<Map<String, Object>> create(@PathVariable Incidente idIncidente, @RequestBody Map<String,Object>request){
        Map<String,Object> response = new HashMap<>();
        try {
            SeguimientoIncidente seguimientoIncidente = new SeguimientoIncidente();
            seguimientoIncidente.setIncidente(idIncidente);
            seguimientoIncidente.setDescripcion(request.get("descripcion").toString());
            LocalDateTime fechaActual = LocalDateTime.now();
            seguimientoIncidente.setFecha(fechaActual);
            seguimientoIncidente.setSugerencia(request.get("sugerencia").toString());
            this.seguimientoIncidenteServiceImp.crearSeguimientoIncidente(seguimientoIncidente);
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
            List<SeguimientoIncidente> seguimientoIncidenteList = this.seguimientoIncidenteServiceImp.listarSeguimientoIncidentes();
            response.put("status","succes");
            response.put("data", seguimientoIncidenteList);

        }catch (Exception e){
            response.put("status",HttpStatus.BAD_GATEWAY);
            response.put("data",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
