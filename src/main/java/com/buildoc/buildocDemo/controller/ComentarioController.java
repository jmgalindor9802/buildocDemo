package com.buildoc.buildocDemo.controller;

import com.buildoc.buildocDemo.entities.Ciclo;
import com.buildoc.buildocDemo.entities.Comentario;
import com.buildoc.buildocDemo.services.imp.ComentarioServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/comentario/",method = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,
        RequestMethod.HEAD})
@CrossOrigin("*")
public class ComentarioController {
    @Autowired
    private ComentarioServiceImp comentarioServiceImp;

    @PostMapping
    public ResponseEntity<Map<String, Object>> create(@RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        try {
            Comentario comentario = new Comentario();
            comentario.setDescripcion(request.get("descripcion").toString());
            this.comentarioServiceImp.crearComentario(comentario);
            response.put("status", "succes");
            response.put("data", "Registro exitoso");

        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_GATEWAY);
            response.put("data", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("all")
    public ResponseEntity<Map<String, Object>> findAll(){
        Map<String,Object> response = new HashMap<>();
        try {
            List<Comentario> comentarioList = this.comentarioServiceImp.listarComentarios();
            response.put("status","succes");
            response.put("data", comentarioList);

        }catch (Exception e){
            response.put("status",HttpStatus.BAD_GATEWAY);
            response.put("data",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
