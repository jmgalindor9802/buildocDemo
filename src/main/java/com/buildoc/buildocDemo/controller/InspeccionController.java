package com.buildoc.buildocDemo.controller;

import com.buildoc.buildocDemo.entities.Ciclo;
import com.buildoc.buildocDemo.entities.Inspeccion;
import com.buildoc.buildocDemo.services.imp.InspeccionServiceImp;
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
@RequestMapping(path = "/api/inspeccion/",method = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,
        RequestMethod.HEAD})
@CrossOrigin("*")
public class InspeccionController {
    @Autowired
    private InspeccionServiceImp inspeccionServiceImp;
    @PostMapping
    public ResponseEntity<Map<String, Object>> create(@RequestBody Map<String,Object>request){
        Map<String, Object> response = new HashMap<>();
        try {
            Inspeccion inspeccion = new Inspeccion();
            String periodicidadInspeccion = request.get("periodicidad").toString();
            Inspeccion.InspeccionPeriodicidad inspeccionPeriodicidad;
            switch (periodicidadInspeccion) {
                case "DIARIA":
                    inspeccionPeriodicidad = Inspeccion.InspeccionPeriodicidad.DIARIA;
                    break;
                case "SEMANAL":
                    inspeccionPeriodicidad = Inspeccion.InspeccionPeriodicidad.SEMANAL;
                    break;
                case "MENSUAL":
                    inspeccionPeriodicidad = Inspeccion.InspeccionPeriodicidad.MENSUAL;
                    break;
                case "NINGUNA":
                    inspeccionPeriodicidad = Inspeccion.InspeccionPeriodicidad.NINGUNA;
                    break;
                default:
                    throw new IllegalArgumentException("Estado de ciclo no válido: " + periodicidadInspeccion);
            }
            inspeccion.setPeriodicidad(inspeccionPeriodicidad);

            this.inspeccionServiceImp.crearInspeccion(inspeccion);
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
            List<Inspeccion> inspeccionList = this.inspeccionServiceImp.listarIncidentes();
            response.put("status","succes");
            response.put("data", inspeccionList);

        }catch (Exception e){
            response.put("status",HttpStatus.BAD_GATEWAY);
            response.put("data",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
