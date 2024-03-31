package com.buildoc.buildocDemo.controller;

import com.buildoc.buildocDemo.entities.Archivo;
import com.buildoc.buildocDemo.entities.Ciclo;
import com.buildoc.buildocDemo.entities.Proyecto;
import com.buildoc.buildocDemo.entities.Usuario;
import com.buildoc.buildocDemo.entities.enums.EstadoCiclo;
import com.buildoc.buildocDemo.services.imp.CicloServiceImp;
import com.buildoc.buildocDemo.services.imp.ProyectoServiceImp;
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
@RequestMapping(path = "/buildoc/ciclo/",method = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,
        RequestMethod.HEAD})
@CrossOrigin("*")
public class CicloController {
    @Autowired
    private CicloServiceImp cicloServiceImp;
    @Autowired
    private ProyectoServiceImp proyectoServiceImp;
    @PostMapping("create")
    public ResponseEntity<Map<String, Object>> create(@RequestBody Map<String,Object>request){
        Map<String, Object> response = new HashMap<>();
        try {
            Ciclo ciclo = new Ciclo();
            ciclo.setEstado(EstadoCiclo.PENDIENTE);
            ciclo.setNombre(request.get("nombre").toString());
            ciclo.setFechaCreacion(LocalDateTime.now());
            ciclo.setDescripcion(request.get("descripcion").toString());
            Long proyectoId = Long.parseLong(request.get("idProyecto").toString());
            Proyecto proyecto = proyectoServiceImp.obtenerProyectoPorId(proyectoId);
            if (proyecto == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            ciclo.setProyecto(proyecto);


            this.cicloServiceImp.crearCiclo(ciclo);
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
            List<Ciclo> cicloList = this.cicloServiceImp.listarCiclos();
            response.put("status","succes");
            response.put("data", cicloList);

        }catch (Exception e){
            response.put("status",HttpStatus.BAD_GATEWAY);
            response.put("data",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("proyecto/{proyectoId}")
    public ResponseEntity<Map<String, Object>> findByProyectoId(@PathVariable Long proyectoId){
        Map<String,Object> response = new HashMap<>();
        try {
            List<Ciclo> cicloList = this.cicloServiceImp.listarCiclosPorProyecto(proyectoId);
            response.put("status","success");
            response.put("data", cicloList);
        } catch (Exception e) {
            response.put("status",HttpStatus.BAD_GATEWAY);
            response.put("data",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
