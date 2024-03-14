package com.buildoc.buildocDemo.controller;

import com.buildoc.buildocDemo.entities.*;
import com.buildoc.buildocDemo.entities.enums.EstadoTarea;
import com.buildoc.buildocDemo.services.imp.ArchivoServiceImp;
import com.buildoc.buildocDemo.services.imp.TipoInspeccionServicesImp;
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
@RequestMapping(path = "/api/tipoinspeccion/",method = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.HEAD})
@CrossOrigin("*")
public class TipoInspeccionController {
    @Autowired
    private TipoInspeccionServicesImp tipoInspeccionServicesImp;

    @Autowired
    private ArchivoServiceImp archivoServiceImp;
    @PostMapping("create")
    public ResponseEntity<Map<String, Object>> create(@RequestBody Map<String,Object>request){
        Map<String,Object> response = new HashMap<>();
        try {
            TipoInspeccion tipoInspeccion = new TipoInspeccion();
            tipoInspeccion.setNombre(request.get("nombre").toString());
            tipoInspeccion.setDescripcion(request.get("descripcion").toString());

            String idArchivosString = request.get("idArchivos").toString();
            String[] idArchivosArray = idArchivosString.split(",");
            List<Long> idArchivos = new ArrayList<>();

            for (String idRole : idArchivosArray) {
                idArchivos.add(Long.parseLong(idRole));
            }

            List<Archivo> archivos= new ArrayList<>();
            for (Long archivoId : idArchivos) {
                Archivo archivo = archivoServiceImp.obtenerArchivoPorId(archivoId);
                archivos.add(archivo);
            }
            tipoInspeccion.setArchivos(archivos);

            this.tipoInspeccionServicesImp.crearTipoInspeccion(tipoInspeccion);
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
            List<TipoInspeccion> tipoInspeccionList = this.tipoInspeccionServicesImp.listarTipoInspecciones();
            response.put("status","succes");
            response.put("data", tipoInspeccionList);

        }catch (Exception e){
            response.put("status",HttpStatus.BAD_GATEWAY);
            response.put("data",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
