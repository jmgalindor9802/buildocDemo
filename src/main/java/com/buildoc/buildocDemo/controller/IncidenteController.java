package com.buildoc.buildocDemo.controller;

import com.buildoc.buildocDemo.dto.IncidenteDto;
import com.buildoc.buildocDemo.entities.Incidente;
import com.buildoc.buildocDemo.entities.Inspeccion;
import com.buildoc.buildocDemo.entities.Proyecto;
import com.buildoc.buildocDemo.entities.enums.EstadoDato;
import com.buildoc.buildocDemo.entities.enums.EstadoResultadoInspeccion;
import com.buildoc.buildocDemo.entities.enums.IncidenteEstado;
import com.buildoc.buildocDemo.entities.enums.IncidenteGravedad;
import com.buildoc.buildocDemo.services.imp.IncidenteServiceImp;
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
@RequestMapping(path = "/buildoc/incidente/",method = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,
        RequestMethod.HEAD})
@CrossOrigin("*")
public class IncidenteController {
    @Autowired
    private IncidenteServiceImp incidenteServiceImp;

    @Autowired
    private ProyectoServiceImp proyectoServiceImp;
    @PostMapping ("create")
    public ResponseEntity<Map<String, Object>> create( @RequestBody Map<String,Object>request){
        Map<String, Object> response = new HashMap<>();
        try {
            Incidente incidente = new Incidente();
            String gravedadIncidente = request.get("gravedad").toString();
            IncidenteGravedad incidenteGravedad;
            switch (gravedadIncidente) {
                case "ALTO":
                    incidenteGravedad = IncidenteGravedad.ALTO;
                    break;
                case "MEDIO":
                    incidenteGravedad = IncidenteGravedad.MEDIO;
                    break;
                case "BAJO":
                    incidenteGravedad = IncidenteGravedad.BAJO;
                    break;
                default:
                    throw new IllegalArgumentException("Gravedad de incidente no válida: " + gravedadIncidente);
            }
            incidente.setNombre(request.get("nombre").toString());
            incidente.setDescripcion(request.get("descripcion").toString());
            incidente.setEstado(IncidenteEstado.INICIALIZADO);
            incidente.setGravedad(incidenteGravedad);
            incidente.setFecha(LocalDateTime.now());
            incidente.setEstadoDato(EstadoDato.ACTIVO);
            incidente.setSugerencias(request.get("sugerencias").toString());

            Long idProyecto = Long.parseLong(request.get("idProyecto").toString());
            Proyecto proyecto = proyectoServiceImp.obtenerProyectoPorId(idProyecto);
            if (proyecto == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            incidente.setProyecto(proyecto);

            this.incidenteServiceImp.crearIncidente(incidente);
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
            List<Incidente> incidenteList = this.incidenteServiceImp.listarEntidadesActivas();
            response.put("status","succes");
            response.put("data", incidenteList);

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
            Incidente incidente = incidenteServiceImp.obtenerIncidentePorId(id);

            if (incidente == null){
                response.put("status", HttpStatus.NOT_FOUND);
                response.put("data", "Incidente no encontrado");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            String gravedadIncidente = request.get("gravedad").toString();
            IncidenteGravedad incidenteGravedad;
            switch (gravedadIncidente) {
                case "ALTO":
                    incidenteGravedad = IncidenteGravedad.ALTO;
                    break;
                case "MEDIO":
                    incidenteGravedad = IncidenteGravedad.MEDIO;
                    break;
                case "BAJO":
                    incidenteGravedad = IncidenteGravedad.BAJO;
                    break;
                default:
                    throw new IllegalArgumentException("Gravedad de incidente no válida: " + gravedadIncidente);
            }
            incidente.setNombre(request.get("nombre").toString());
            incidente.setDescripcion(request.get("descripcion").toString());
            incidente.setEstado(IncidenteEstado.INICIALIZADO);
            incidente.setGravedad(incidenteGravedad);
            incidente.setFecha(LocalDateTime.now());
            incidente.setSugerencias(request.get("sugerencias").toString());

            Long idProyecto = Long.parseLong(request.get("idProyecto").toString());
            Proyecto proyecto = proyectoServiceImp.obtenerProyectoPorId(idProyecto);
            if (proyecto == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            incidente.setProyecto(proyecto);


            this.incidenteServiceImp.actualizarIncidente(incidente);
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
            Incidente incidente = incidenteServiceImp.obtenerIncidentePorId(id);

            if (incidente == null){
                response.put("status", HttpStatus.NOT_FOUND);
                response.put("data", "Usuario no encontrado");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            incidenteServiceImp.cambiarEstadoDato(id, EstadoDato.DESACTIVADO);

            response.put("status", "success");
            response.put("data", "Usuario deshabilitado correctamente");

        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_GATEWAY);
            response.put("data", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        try {
            // Obtener el incidente por su ID
            Incidente incidente = incidenteServiceImp.obtenerIncidentePorId(id);

            // Verificar si el incidente existe
            if (incidente == null){
                response.put("status", HttpStatus.NOT_FOUND);
                response.put("data", "Incidente no encontrado");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            IncidenteDto incidenteDto= new IncidenteDto();
            incidenteDto.setProyecto(incidente.getProyecto().getNombre());
            incidenteDto.setDescripcion(incidente.getDescripcion());
            incidenteDto.setNombre(incidente.getNombre());
            incidenteDto.setFechaReporte(incidente.getFecha().toString());

            response.put("status","success");
            response.put("data", incidenteDto);


        } catch (Exception e){
            response.put("status", HttpStatus.BAD_GATEWAY);
            response.put("data", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
