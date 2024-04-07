package com.buildoc.buildocDemo.controller;

import com.buildoc.buildocDemo.entities.*;
import com.buildoc.buildocDemo.entities.enums.EstadoDato;
import com.buildoc.buildocDemo.services.imp.IncidenteServiceImp;
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
@RequestMapping(path = "/buildoc/seguimientoincidente/",method = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.HEAD})
@CrossOrigin("*")
public class SeguimientoIncidenteController {
    @Autowired
    private SeguimientoIncidenteServiceImp seguimientoIncidenteServiceImp;

    @Autowired
    private IncidenteServiceImp incidenteServiceImp;

    @PostMapping("create/{id}")
    public ResponseEntity<Map<String, Object>> create( @PathVariable Long id, @RequestBody Map<String,Object>request){
        Map<String,Object> response = new HashMap<>();
        try {
            SeguimientoIncidente seguimientoIncidente = new SeguimientoIncidente();
            seguimientoIncidente.setDescripcion(request.get("descripcion").toString());
            LocalDateTime fechaActual = LocalDateTime.now();
            seguimientoIncidente.setEstadoDato(EstadoDato.ACTIVO);
            seguimientoIncidente.setFecha(fechaActual);
            seguimientoIncidente.setSugerencia(request.get("sugerencia").toString());
            Incidente incidente = incidenteServiceImp.obtenerIncidentePorId(id);

            if (incidente == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            seguimientoIncidente.setIncidente(incidente);

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
            List<SeguimientoIncidente> seguimientoIncidenteList = this.seguimientoIncidenteServiceImp.listarEntidadesActivas();
            response.put("status","succes");
            response.put("data", seguimientoIncidenteList);

        }catch (Exception e){
            response.put("status",HttpStatus.BAD_GATEWAY);
            response.put("data",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            SeguimientoIncidente seguimientoIncidente = seguimientoIncidenteServiceImp.obtenerSeguimientoIncidentePorId(id);

            if (seguimientoIncidente == null){
                response.put("status", HttpStatus.NOT_FOUND);
                response.put("data", "Usuario no encontrado");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            seguimientoIncidenteServiceImp.cambiarEstadoDato(id, EstadoDato.DESACTIVADO);

            response.put("status", "success");
            response.put("data", "Usuario deshabilitado correctamente");

        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_GATEWAY);
            response.put("data", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
