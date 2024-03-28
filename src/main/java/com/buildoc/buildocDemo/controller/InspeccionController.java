package com.buildoc.buildocDemo.controller;

import com.buildoc.buildocDemo.entities.*;
import com.buildoc.buildocDemo.entities.enums.InspeccionPeriodicidad;
import com.buildoc.buildocDemo.services.imp.InspeccionServiceImp;
import com.buildoc.buildocDemo.services.imp.TareaServiceImp;
import com.buildoc.buildocDemo.services.imp.TipoInspeccionServicesImp;
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
@RequestMapping(path = "/buildoc/inspeccion/",method = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,
        RequestMethod.HEAD})
@CrossOrigin("*")
public class InspeccionController {
    @Autowired
    private InspeccionServiceImp inspeccionServiceImp;

    @Autowired
    private TipoInspeccionServicesImp tipoInspeccionServicesImp;
    @Autowired
    private TareaServiceImp tareaServiceImp;
    @PostMapping ("create")
    public ResponseEntity<Map<String, Object>> create(@RequestBody Map<String,Object>request){
        Map<String, Object> response = new HashMap<>();
        try {
            Inspeccion inspeccion = new Inspeccion();

            Long idTipoInspeccion = Long.parseLong(request.get("idTipoInspeccion").toString());
            TipoInspeccion tipoInspeccion = tipoInspeccionServicesImp.obtenerTipoInspeccionPorId(idTipoInspeccion);

            if (tipoInspeccion == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            inspeccion.setTipoInspeccion(tipoInspeccion);

            Long idTarea = Long.parseLong(request.get("idTarea").toString());
            Tarea tarea = tareaServiceImp.obtenerTareaPorId(idTarea);

            if (tarea == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            inspeccion.setTarea_inspeccion(tarea);

            String periodicidadInspeccion = request.get("periodicidad").toString();
            InspeccionPeriodicidad inspeccionPeriodicidad;
            switch (periodicidadInspeccion) {
                case "DIARIA":
                    inspeccionPeriodicidad = InspeccionPeriodicidad.DIARIA;
                    break;
                case "SEMANAL":
                    inspeccionPeriodicidad = InspeccionPeriodicidad.SEMANAL;
                    break;
                case "MENSUAL":
                    inspeccionPeriodicidad = InspeccionPeriodicidad.MENSUAL;
                    break;
                case "NINGUNA":
                    inspeccionPeriodicidad = InspeccionPeriodicidad.NINGUNA;
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

    @PutMapping("update/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable Long id, @RequestBody Map<String,Object>request){
        Map<String, Object> response = new HashMap<>();
        try {
            Inspeccion inspeccion = inspeccionServiceImp.obtenerInspeccionPorId(id);

            if (inspeccion == null){
                response.put("status", HttpStatus.NOT_FOUND);
                response.put("data", "Incidente no encontrado");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            Long idTipoInspeccion = Long.parseLong(request.get("idTipoInspeccion").toString());
            TipoInspeccion tipoInspeccion = tipoInspeccionServicesImp.obtenerTipoInspeccionPorId(idTipoInspeccion);

            if (tipoInspeccion == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            inspeccion.setTipoInspeccion(tipoInspeccion);

            Long idTarea = Long.parseLong(request.get("idTarea").toString());
            Tarea tarea = tareaServiceImp.obtenerTareaPorId(idTarea);

            if (tarea == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            inspeccion.setTarea_inspeccion(tarea);

            String periodicidadInspeccion = request.get("periodicidad").toString();
            InspeccionPeriodicidad inspeccionPeriodicidad;
            switch (periodicidadInspeccion) {
                case "DIARIA":
                    inspeccionPeriodicidad = InspeccionPeriodicidad.DIARIA;
                    break;
                case "SEMANAL":
                    inspeccionPeriodicidad = InspeccionPeriodicidad.SEMANAL;
                    break;
                case "MENSUAL":
                    inspeccionPeriodicidad = InspeccionPeriodicidad.MENSUAL;
                    break;
                case "NINGUNA":
                    inspeccionPeriodicidad = InspeccionPeriodicidad.NINGUNA;
                    break;
                default:
                    throw new IllegalArgumentException("Estado de ciclo no válido: " + periodicidadInspeccion);
            }
            inspeccion.setPeriodicidad(inspeccionPeriodicidad);

            this.inspeccionServiceImp.actualizarInspeccion(inspeccion);
            response.put("status","succes");
            response.put("data","Registro exitoso");

        }catch (Exception e){
            response.put("status", HttpStatus.BAD_GATEWAY);
            response.put("data",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Inspeccion inspeccion = inspeccionServiceImp.obtenerInspeccionPorId(id);

            if (inspeccion == null){
                response.put("status", HttpStatus.NOT_FOUND);
                response.put("data", "Inspección no encontrada");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            inspeccionServiceImp.eliminarInspeccion(inspeccion);
            response.put("status","succes");
            response.put("data","Inspección eliminada correctamente");

        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_GATEWAY);
            response.put("data", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
