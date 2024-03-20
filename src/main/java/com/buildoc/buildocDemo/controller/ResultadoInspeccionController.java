package com.buildoc.buildocDemo.controller;
import com.buildoc.buildocDemo.entities.ResultadoInspeccion;
import com.buildoc.buildocDemo.entities.enums.EstadoResultadoInspeccion;
import com.buildoc.buildocDemo.services.imp.ResultadoInspeccionServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping(path = "/api/resultadoinspeccion/",method = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.HEAD})
@CrossOrigin("*")
public class ResultadoInspeccionController {
    @Autowired
    private ResultadoInspeccionServiceImp resultadoInspeccionServicesImp;
    @PostMapping("create")
    public ResponseEntity<Map<String, Object>> create(@RequestBody Map<String,Object>request){
        Map<String,Object> response = new HashMap<>();
        try {
            ResultadoInspeccion resultadoInspeccion = new ResultadoInspeccion();
            resultadoInspeccion.setDescripcion(request.get("descripcion").toString());
            resultadoInspeccion.setIdInspeccion(((Number) request.get("idInspeccion")).longValue());
            resultadoInspeccion.setEstado(EstadoResultadoInspeccion.EN_REVISIÓN);
            resultadoInspeccion.setObservacionesGenerales(request.get("observaciones").toString());

            this.resultadoInspeccionServicesImp.crearResultadoInspeccion(resultadoInspeccion);
            response.put("status","succes");
            response.put("data","Registro exitoso");

        }catch (Exception e){
            response.put("status",HttpStatus.BAD_GATEWAY);
            response.put("data",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("all")
    public ResponseEntity<Map<String, Object>> findAll(){
        Map<String,Object> response = new HashMap<>();
        try {
            List<ResultadoInspeccion> resultadosInspeccionList = this.resultadoInspeccionServicesImp.listarResultadosInspeccion();
            response.put("status","succes");
            response.put("data", resultadosInspeccionList);

        }catch (Exception e){
            response.put("status",HttpStatus.BAD_GATEWAY);
            response.put("data",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PutMapping("update/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable Long id, @RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        try {
            ResultadoInspeccion resultadoInspeccion = resultadoInspeccionServicesImp.obtenerResultadoInspeccionPorId(id);
            if (resultadoInspeccion == null) {
                response.put("status", HttpStatus.NOT_FOUND);
                response.put("data", "No se encontró ningún resultado de inspección con el ID proporcionado");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            // Actualizar los campos específicos del resultado de inspección

            if (request.containsKey("estado")) {
                // Si el estado es proporcionado en la solicitud, asegúrate de validar su valor
                String estado = request.get("estado").toString();
                if (estado.equalsIgnoreCase(EstadoResultadoInspeccion.EN_REVISIÓN.toString()) ||
                        estado.equalsIgnoreCase(EstadoResultadoInspeccion.APROBADO.toString()) ||
                        estado.equalsIgnoreCase(EstadoResultadoInspeccion.NO_APROBADO.toString())) {
                    resultadoInspeccion.setEstado(EstadoResultadoInspeccion.valueOf(estado.toUpperCase()));
                } else {
                    // Si el estado proporcionado no es válido, devuelve un error
                    response.put("status", HttpStatus.BAD_REQUEST);
                    response.put("data", "El estado proporcionado no es válido");
                    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
                }
            }

            // Guardar los cambios en la base de datos
            resultadoInspeccion.setFechaModificacion(LocalDateTime.now());
            resultadoInspeccionServicesImp.actualizarResultadoInspeccion(resultadoInspeccion);

            response.put("status", "success");
            response.put("data", "Resultado de inspección actualizado exitosamente");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_GATEWAY);
            response.put("data", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
    }


}