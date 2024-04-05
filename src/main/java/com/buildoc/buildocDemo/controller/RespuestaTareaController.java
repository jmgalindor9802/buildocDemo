package com.buildoc.buildocDemo.controller;

import com.buildoc.buildocDemo.dto.RespuestaTareaDto;
import com.buildoc.buildocDemo.entities.RespuestaTarea;
import com.buildoc.buildocDemo.entities.Tarea;
import com.buildoc.buildocDemo.entities.Usuario;
import com.buildoc.buildocDemo.entities.enums.EstadoRespuestaTarea;
import com.buildoc.buildocDemo.entities.enums.EstadoTarea;
import com.buildoc.buildocDemo.services.imp.RespuestaTareaServiceImp;
import com.buildoc.buildocDemo.services.imp.TareaServiceImp;
import com.buildoc.buildocDemo.services.imp.UsuarioServiceImp;
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
@RequestMapping(path = "/buildoc/respuestatarea/", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@CrossOrigin("*")
public class RespuestaTareaController {

    @Autowired
    private RespuestaTareaServiceImp respuestaTareaServiceImp;
    @Autowired
    private TareaServiceImp tareaServiceImp;
    @Autowired
    private UsuarioServiceImp usuarioServiceImp;

    @PostMapping("create/{id}")
    public ResponseEntity<Map<String, Object>> create(@PathVariable Long id ,@RequestBody Map<String,Object>request) {
        Map<String, Object> response = new HashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        try {
            RespuestaTarea respuestaTarea = new RespuestaTarea();
            respuestaTarea.setFechaEntrega(LocalDateTime.now());
            respuestaTarea.setEstado(EstadoRespuestaTarea.EN_REVISION);
            respuestaTarea.setComentario(request.get("comentario").toString());

            Tarea tarea = tareaServiceImp.obtenerTareaPorId(id);
            tarea.setEstado(EstadoTarea.COMPLETADO);
            if (tarea == null) {
                response.put("status", HttpStatus.NOT_FOUND);
                response.put("data", "No se encontró ninguna tarea con el ID proporcionado");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            respuestaTarea.setTarea(tarea);
      /*
            Long idUsuario=Long.parseLong(request.get("idUsuario").toString());
            Usuario usuario = usuarioServiceImp.obtenerUsuarioPorId(idUsuario);

            if (usuario== null) {
                response.put("status", HttpStatus.NOT_FOUND);
                response.put("data", "No se encontró ninguna tarea con el ID proporcionado");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
          respuestaTarea.setUsuario(usuario);
          */

            respuestaTareaServiceImp.crearRespuestaTarea(respuestaTarea);
            response.put("status", "success");
            response.put("data", "Respuesta de tarea creada exitosamente");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_REQUEST);
            response.put("data", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("all")
    public ResponseEntity<Map<String, Object>> findAll(@RequestParam Long idTarea) {
        Map<String, Object> response = new HashMap<>();
        try {
            Tarea tarea = tareaServiceImp.obtenerTareaPorId(idTarea);
            List<RespuestaTarea> respuestasTareaList = this.respuestaTareaServiceImp.obtenerRespuestasTareaPorTarea(idTarea);

            // Mapear las entidades RespuestaTarea a DTOs RespuestaTareaDto
            List<RespuestaTareaDto> respuestasTareaDtoList = new ArrayList<>();
            for (RespuestaTarea respuestaTarea : respuestasTareaList) {
                RespuestaTareaDto respuestaTareaDto = new RespuestaTareaDto();
                respuestaTareaDto.setId(respuestaTarea.getIdRespuestaTarea());
                respuestaTareaDto.setFechaEntrega(respuestaTarea.getFechaEntrega());
                respuestaTareaDto.setEstado(respuestaTarea.getEstado());
                respuestaTareaDto.setComentario(respuestaTarea.getComentario());
                respuestaTareaDto.setIdTarea(respuestaTarea.getTarea().getIdTarea());
                respuestaTareaDto.setIdUsuario(respuestaTarea.getUsuario().getId());

                respuestasTareaDtoList.add(respuestaTareaDto);
            }

            response.put("status", HttpStatus.OK);
            response.put("data", respuestasTareaDtoList);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
            response.put("data", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("update/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable Long id, @RequestBody RespuestaTarea updatedRespuestaTarea) {
        Map<String, Object> response = new HashMap<>();
        try {
            RespuestaTarea existingRespuestaTarea = respuestaTareaServiceImp.obtenerRespuestaTareaPorId(id);
            if (existingRespuestaTarea == null) {
                response.put("status", HttpStatus.NOT_FOUND);
                response.put("data", "No se encontró ninguna respuesta de tarea con el ID proporcionado");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            // Actualizar los campos específicos de la respuesta de tarea

            // Aquí puedes realizar cualquier lógica adicional para la actualización de campos

            respuestaTareaServiceImp.actualizarRespuestaTarea(updatedRespuestaTarea);
            response.put("status", "success");
            response.put("data", "Respuesta de tarea actualizada exitosamente");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
            response.put("data", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            respuestaTareaServiceImp.eliminarRespuestaTarea(id);
            response.put("status", "success");
            response.put("data", "Respuesta de tarea eliminada exitosamente");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
            response.put("data", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
