package com.buildoc.buildocDemo.controller;

import com.buildoc.buildocDemo.entities.Archivo;
import com.buildoc.buildocDemo.entities.Ciclo;
import com.buildoc.buildocDemo.entities.Tarea;
import com.buildoc.buildocDemo.entities.enums.EstadoTarea;
import com.buildoc.buildocDemo.services.imp.ArchivoServiceImp;
import com.buildoc.buildocDemo.services.imp.CicloServiceImp;
import com.buildoc.buildocDemo.services.imp.TareaServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/buildoc/tarea/",method = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.HEAD})
@CrossOrigin("*")
public class TareaController {
    @Autowired
    private TareaServiceImp tareaServiceImp;
    @Autowired
    private CicloServiceImp cicloServiceImp;

    @Autowired
    private ArchivoServiceImp archivoServiceImp;

    @PostMapping("create")
    public ResponseEntity<Map<String, Object>> create(@RequestBody Map<String,Object>request){
        Map<String,Object> response = new HashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        try {
            Tarea tarea = new Tarea();
           tarea.setNombre(request.get("nombre").toString());
           tarea.setDescripcion(request.get("descripcion").toString());
           tarea.setEstado(EstadoTarea.PENDIENTE);
           tarea.setFechaCreacion(LocalDateTime.now());
            LocalDateTime parsedDateTime = LocalDateTime.parse(request.get("fechaLimite").toString(), formatter);
           tarea.setFechaLimite(parsedDateTime);
           Long idCiclo=Long.parseLong(request.get("idCiclo").toString());
            Ciclo ciclo=cicloServiceImp.obtenerCicloPorId(idCiclo);
            if (ciclo == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            tarea.setCiclo(ciclo);

            if (request.containsKey("idArchivos")) {
                String idArchivosString = request.get("idArchivos").toString();
                String[] idArchivosArray = idArchivosString.split(",");
                List<Long> idArchivos = new ArrayList<>();

                for (String idRole : idArchivosArray) {
                    idArchivos.add(Long.parseLong(idRole));
                }
                List<Archivo> archivos = new ArrayList<>();
                for (Long archivoId : idArchivos) {
                    Archivo archivo = archivoServiceImp.obtenerArchivoPorId(archivoId);
                    archivos.add(archivo);
                }
                tarea.setArchivos(archivos);
            }

            this.tareaServiceImp.crearTarea(tarea);
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
            List<Tarea> tareaList = this.tareaServiceImp.listarTareas();
            response.put("status","succes");
            response.put("data", tareaList);

        }catch (Exception e){
            response.put("status",HttpStatus.BAD_GATEWAY);
            response.put("data",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
