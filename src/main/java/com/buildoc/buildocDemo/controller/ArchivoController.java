package com.buildoc.buildocDemo.controller;

import com.buildoc.buildocDemo.entities.Archivo;
import com.buildoc.buildocDemo.entities.Usuario;
import com.buildoc.buildocDemo.services.imp.ArchivoServiceImp;
import com.buildoc.buildocDemo.services.imp.UsuarioServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.time.format.DateTimeFormatter;

import java.time.LocalDateTime;
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
    @Transactional
    @PostMapping
    public ResponseEntity<Map<String, Object>> create(@RequestBody Map<String,Object>request){
        Map<String, Object> response = new HashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        try {
            Archivo archivo = new Archivo();
            LocalDateTime parsedDateTime = LocalDateTime.parse(request.get("fechaCreacion").toString(), formatter);

            archivo.setNombreOriginal(request.get("nombreOriginal").toString());
            archivo.setFechaCreacion(parsedDateTime);
            archivo.setTipo(request.get("tipo").toString());
            archivo.setTamano(request.get("tamano").toString());
            archivo.setRuta(request.get("ruta").toString());
            Long usuarioId = Long.parseLong(request.get("usuarioId").toString());
            Usuario usuario = usuarioServiceImp.obtenerUsuarioPorId(usuarioId);
            if (usuario == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            archivo.setUsuario(usuario);

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