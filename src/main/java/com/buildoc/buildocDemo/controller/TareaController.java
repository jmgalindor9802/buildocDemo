package com.buildoc.buildocDemo.controller;

import com.buildoc.buildocDemo.dto.TareaDto;
import com.buildoc.buildocDemo.dto.UsuarioDto;
import com.buildoc.buildocDemo.entities.Archivo;
import com.buildoc.buildocDemo.entities.Ciclo;
import com.buildoc.buildocDemo.entities.Tarea;
import com.buildoc.buildocDemo.entities.Usuario;
import com.buildoc.buildocDemo.entities.enums.EstadoDato;
import com.buildoc.buildocDemo.entities.enums.EstadoTarea;
import com.buildoc.buildocDemo.services.imp.ArchivoServiceImp;
import com.buildoc.buildocDemo.services.imp.CicloServiceImp;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/buildoc/tarea/",method = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.HEAD})
@CrossOrigin("*")
public class TareaController {
    @Autowired
    private TareaServiceImp tareaServiceImp;
    @Autowired
    private UsuarioServiceImp usuarioServiceImp;
    @Autowired
    private CicloServiceImp cicloServiceImp;

    @Autowired
    private ArchivoServiceImp archivoServiceImp;

    @PostMapping("create")
    public ResponseEntity<Map<String, Object>> create(@RequestBody Map<String,Object>request){
        Map<String,Object> response = new HashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        try {
            Tarea tarea = new Tarea();
           tarea.setNombre(request.get("nombre").toString());
           tarea.setDescripcion(request.get("descripcion").toString());
           tarea.setEstado(EstadoTarea.PENDIENTE);
           tarea.setFechaCreacion(LocalDateTime.now());
            LocalDateTime parsedDateTime_fechaLimite = LocalDateTime.parse(request.get("fechaLimite").toString(), formatter);
           tarea.setFechaLimite(parsedDateTime_fechaLimite);
            LocalDateTime parsedDateTime_fechaInicial = LocalDateTime.parse(request.get("fechaInicial").toString(), formatter);
            tarea.setFechaInicial(parsedDateTime_fechaInicial);
            tarea.setEstadoDato(EstadoDato.ACTIVO);

           Long idCiclo=Long.parseLong(request.get("idCiclo").toString());
            Ciclo ciclo=cicloServiceImp.obtenerCicloPorId(idCiclo);
            if (ciclo == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            tarea.setCiclo(ciclo);

            if (request.containsKey("idArchivos")) {
                // La clave "idArchivos" está presente en la solicitud
                String idArchivosString = request.get("idArchivos").toString();
                String[] idArchivosArray = idArchivosString.split(",");
                List<Long> idArchivos = new ArrayList<>();
                // Convertir los IDs de archivos a Long y agregarlos a la lista
                for (String idArchivo : idArchivosArray) {
                    idArchivos.add(Long.parseLong(idArchivo.trim())); // Trim para eliminar espacios en blanco alrededor del ID
                }

                List<Archivo> archivos = new ArrayList<>();
                for (Long idArchivo : idArchivos) {
                    Archivo archivo = archivoServiceImp.obtenerArchivoPorId(idArchivo);
                    archivos.add(archivo);
                }
                tarea.setArchivos(archivos);
            } else {
                tarea.setArchivos(new ArrayList<>());
            }

            if (request.containsKey("idUsuarios")) {
                String idUsuariosString = request.get("idUsuarios").toString();
                String[] idUsuariosArray = idUsuariosString.split(",");
                List<Long> idUsuarios = new ArrayList<>();
                for (String idUsuario : idUsuariosArray) {
                    idUsuarios.add(Long.parseLong(idUsuario.trim()));
                }
                List<Usuario> usuarios = new ArrayList<>();
                for (Long idUsuario : idUsuarios) {
                    Usuario usuario = usuarioServiceImp.obtenerUsuarioPorId(idUsuario);
                    usuarios.add(usuario);
                }
                tarea.setUsuario(usuarios);
            } else {
                tarea.setUsuario(new ArrayList<>());
            }

            Long tareaId = this.tareaServiceImp.crearTarea(tarea);
            response.put("status","succes");
            response.put("data","Registro exitoso");
            response.put("tareaId", tareaId);

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
            List<Tarea> tareaList = this.tareaServiceImp.listarEntidadesActivas();
            response.put("status","succes");
            response.put("data", tareaList);

        }catch (Exception e){
            response.put("status",HttpStatus.BAD_GATEWAY);
            response.put("data",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Tarea tarea = tareaServiceImp.obtenerTareaPorId(id);
            if (tarea == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            TareaDto tareaDto = new TareaDto();
            tareaDto.setNombre(tarea.getNombre());
            tareaDto.setCiclo(tarea.getCiclo().getNombre());

            tareaDto.setDescripcion(tarea.getDescripcion());
            tareaDto.setProyecto(tarea.getCiclo().getProyecto().getNombre());
            String responsablesString = tarea.getUsuario().stream()
                    .map(Usuario::getId)
                    .map(String::valueOf) // Convertir cada ID a String
                    .collect(Collectors.joining(",")); // Unir los IDs con comas

            tareaDto.setResponsables(responsablesString);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            String fechaInicialFormateada = tarea.getFechaInicial().format(formatter);
            String fechaFinalFormateada = tarea.getFechaLimite().format(formatter);
            tareaDto.setFechaFinal(fechaFinalFormateada);
            tareaDto.setFechaInicial(fechaInicialFormateada);
            response.put("status", HttpStatus.OK);
            response.put("data", tareaDto);
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
            Tarea tarea = tareaServiceImp.obtenerTareaPorId(id);

            if (tarea == null){
                response.put("status", HttpStatus.NOT_FOUND);
                response.put("data", "Usuario no encontrado");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            tareaServiceImp.cambiarEstadoDato(id, EstadoDato.DESACTIVADO);

            response.put("status", "success");
            response.put("data", "Usuario deshabilitado correctamente");

        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_GATEWAY);
            response.put("data", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable Long id, @RequestBody TareaDto tareaDto) {
        Map<String, Object> response = new HashMap<>();
        try {
            // Verificar si la tarea existe
            Tarea tarea = tareaServiceImp.obtenerTareaPorId(id);
            if (tarea == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            tarea.setDescripcion(tareaDto.getDescripcion());
            String fechaStringInicial = (String) tareaDto.getFechaInicial();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            LocalDateTime fechaInicial = LocalDateTime.parse(fechaStringInicial, formatter);
            tarea.setFechaInicial(fechaInicial);
            String fechaStringFinal = (String) tareaDto.getFechaFinal();
            LocalDateTime fechaFinal = LocalDateTime.parse(fechaStringFinal, formatter);
            tarea.setFechaLimite(fechaFinal);


            tareaServiceImp.actualizarTarea(tarea);
            response.put("status", HttpStatus.OK);
            response.put("data", "Tarea actualizada exitosamente");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
            response.put("data", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}

