package com.buildoc.buildocDemo.controller;
import com.buildoc.buildocDemo.entities.ResultadoInspeccion;
import com.buildoc.buildocDemo.entities.enums.EstadoResultadoInspeccion;
import com.buildoc.buildocDemo.services.imp.ResultadoInspeccionServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


}