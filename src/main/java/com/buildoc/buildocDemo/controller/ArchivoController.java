package com.buildoc.buildocDemo.controller;

import com.buildoc.buildocDemo.entities.*;
import com.buildoc.buildocDemo.services.imp.ArchivoServiceImp;
import com.buildoc.buildocDemo.services.imp.TareaServiceImp;
import com.buildoc.buildocDemo.services.imp.TipoInspeccionServicesImp;
import com.buildoc.buildocDemo.services.imp.UsuarioServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.time.format.DateTimeFormatter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/archivo/",method = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,
        RequestMethod.HEAD})
@CrossOrigin("*")
public class ArchivoController {
    @Autowired
    private ArchivoServiceImp archivoServiceImp;
    @Autowired
    private UsuarioServiceImp usuarioServiceImp;
    @Autowired
    private TareaServiceImp tareaServiceImp;
    @Autowired
    TipoInspeccionServicesImp tipoInspeccionServicesImp;
    @Transactional
    @PostMapping("create")
    public ResponseEntity<Map<String, Object>> create(@RequestBody Map<String,Object>request){
        Map<String, Object> response = new HashMap<>();

        try {
            Archivo archivo = new Archivo();

            archivo.setNombreOriginal(request.get("nombreOriginal").toString());
            archivo.setFechaCreacion(LocalDateTime.now());
            archivo.setTipo(request.get("tipo").toString());
            archivo.setTamano(request.get("tamano").toString());
            archivo.setRuta(request.get("ruta").toString());
            Long usuarioId = Long.parseLong(request.get("usuarioId").toString());
            Usuario usuario = usuarioServiceImp.obtenerUsuarioPorId(usuarioId);
            if (usuario == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            archivo.setUsuario(usuario);
            /*Revisar si deja crear el archivo sin nesecidad de existir una tarea en la BD o ingresarla en el json*/
            if (request.containsKey("idTarea")) {
                /*obtener los id en cadena de texto*/
                String tareaIdString = request.get("idTarea").toString();
                /*pasar la cadena de texto a un array*/
                String[] tareaIdArray = tareaIdString.split(",");
                List<Long> tareaId = new ArrayList<>();
                /*Descomponer el array para pasarlo a tipo Long y agregarlo a la lista*/
                for (String idTarea : tareaIdArray) {
                    tareaId.add(Long.parseLong(idTarea));
                }
                /*obtener el objeto y buscarlo*/
                List<Tarea> tareas = new ArrayList<>();
                for (Long idTarea : tareaId) {
                    Tarea tarea = tareaServiceImp.obtenerTareaPorId(idTarea);
                    tareas.add(tarea);
                }

                archivo.setTareas(tareas);
            }

            if (request.containsKey("idTipoInspeccion")) {
                /*obtener los id en cadena de texto*/
                String tipoInspeccionIdString = request.get("idTipoInspeccion").toString();
                /*pasar la cadena de texto a un array*/
                String[] tipoInspeccionIdArray = tipoInspeccionIdString.split(",");
                List<Long> tipoInspeccionId = new ArrayList<>();
                /*Descomponer el array para pasarlo a tipo Long y agregarlo a la lista*/
                for (String idtipoInspeccion : tipoInspeccionIdArray) {
                    tipoInspeccionId.add(Long.parseLong(idtipoInspeccion));
                }
                /*obtener el objeto y buscarlo*/
                List<TipoInspeccion> tipoInspeccionList = new ArrayList<>();
                for (Long idtipoInspeccion : tipoInspeccionId) {
                    TipoInspeccion tipoInspeccion = tipoInspeccionServicesImp.obtenerTipoInspeccionPorId(idtipoInspeccion);
                    tipoInspeccionList.add(tipoInspeccion);
                }

                archivo.setTipoInspecciones(tipoInspeccionList);
            }

            this.archivoServiceImp.crearArchivo(archivo);
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
            List<Archivo> archivoList = this.archivoServiceImp.listarArchivos();
            response.put("status","succes");
            response.put("data", archivoList);

        }catch (Exception e){
            response.put("status",HttpStatus.BAD_GATEWAY);
            response.put("data",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}