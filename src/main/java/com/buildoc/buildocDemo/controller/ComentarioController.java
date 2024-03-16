package com.buildoc.buildocDemo.controller;

import com.buildoc.buildocDemo.entities.Ciclo;
import com.buildoc.buildocDemo.entities.Comentario;
import com.buildoc.buildocDemo.entities.Proyecto;
import com.buildoc.buildocDemo.entities.Tarea;
import com.buildoc.buildocDemo.services.imp.ComentarioServiceImp;
import com.buildoc.buildocDemo.services.imp.TareaServiceImp;
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
    @Autowired
    private TareaServiceImp tareaServiceImp;

    @PostMapping("create")
    public ResponseEntity<Map<String, Object>> create(@RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        try {
            Comentario comentario = new Comentario();
            comentario.setDescripcion(request.get("descripcion").toString());
            Long tareaId = Long.parseLong(request.get("idTarea").toString());
            Tarea tarea = tareaServiceImp.obtenerTareaPorId(tareaId);
            if (tarea == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            comentario.setTarea(tarea);
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

    @PutMapping("update/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable Long id, @RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        try {
            Comentario comentario = comentarioServiceImp.obtenerComentarioPorId(id);
            if (comentario == null){
                response.put("status", HttpStatus.NOT_FOUND);
                response.put("data", "Comentario no encontrado");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            comentario.setDescripcion(request.get("descripcion").toString());
            Long tareaId = Long.parseLong(request.get("idTarea").toString());
            Tarea tarea = tareaServiceImp.obtenerTareaPorId(tareaId);
            if (tarea == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            comentario.setTarea(tarea);
            this.comentarioServiceImp.actualizarComentario(comentario);
            response.put("status", "succes");
            response.put("data", "Registro exitoso");

        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_GATEWAY);
            response.put("data", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Comentario comentario = comentarioServiceImp.obtenerComentarioPorId(id);
            if (comentario == null){
                response.put("status", HttpStatus.NOT_FOUND);
                response.put("data", "Comentario no encontrado");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            comentarioServiceImp.eliminarComentario(comentario);
            response.put("status", "success");
            response.put("data", "Comentario eliminado exitosamente");
        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_GATEWAY);
            response.put("data", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
