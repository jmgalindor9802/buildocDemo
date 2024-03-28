package com.buildoc.buildocDemo.controller;

import com.buildoc.buildocDemo.entities.*;
import com.buildoc.buildocDemo.services.EquipoServices;
import com.buildoc.buildocDemo.services.imp.EquipoServiceImp;
import com.buildoc.buildocDemo.services.imp.ProyectoServiceImp;
import com.buildoc.buildocDemo.services.imp.UsuarioServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/buildoc/equipo/",method = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,
        RequestMethod.HEAD})
@CrossOrigin("*")
public class EquipoController {
    @Autowired
    private EquipoServiceImp equipoServiceImp;
    @Autowired
    private ProyectoServiceImp proyectoServiceImp;
    @Autowired
    private UsuarioServiceImp usuarioServiceImp;
    @PostMapping("create")
    public ResponseEntity<Map<String, Object>> create(@RequestBody Map<String,Object>request){
        Map<String, Object> response = new HashMap<>();
        try {
            Equipo equipo = new Equipo();
            equipo.setNombre(request.get("nombre").toString());
            Long proyectoId = Long.parseLong(request.get("idProyecto").toString());
            Proyecto proyecto = proyectoServiceImp.obtenerProyectoPorId(proyectoId);
            if (proyecto == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            equipo.setProyecto(proyecto);
            String idUsuariosString = request.get("idUsuarios").toString();
            String[] idUsuariosArray = idUsuariosString.split(",");
            List<Long> idUsuarios = new ArrayList<>();

            for (String idUsuario : idUsuariosArray) {
                idUsuarios.add(Long.parseLong(idUsuario));
            }

            List<Usuario> usuarios = new ArrayList<>();
            for (Long usuarioId : idUsuarios) {
                Usuario usuario = usuarioServiceImp.obtenerUsuarioPorId(usuarioId);
                usuarios.add(usuario);
            }

            equipo.setUsuarios(usuarios);

            Long liderId = Long.parseLong(request.get("idLiderEquipo").toString());
            Usuario liderEquipo = usuarioServiceImp.obtenerUsuarioPorId(liderId);
            if (liderEquipo == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            equipo.setLider(liderEquipo);
            this.equipoServiceImp.crearEquipo(equipo);
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
            List<Equipo> equipoList = this.equipoServiceImp.listarEquipos();
            response.put("status","succes");
            response.put("data", equipoList);

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
            Equipo equipo = equipoServiceImp.obtenerEquipoPorId(id);

            if (equipo == null){
                response.put("status", HttpStatus.NOT_FOUND);
                response.put("data", "Equipo no encontrado");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            equipo.setNombre(request.get("nombre").toString());
            Long proyectoId = Long.parseLong(request.get("idProyecto").toString());
            Proyecto proyecto = proyectoServiceImp.obtenerProyectoPorId(proyectoId);
            if (proyecto == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            equipo.setProyecto(proyecto);
            String idUsuariosString = request.get("idUsuarios").toString();
            String[] idUsuariosArray = idUsuariosString.split(",");
            List<Long> idUsuarios = new ArrayList<>();

            for (String idUsuario : idUsuariosArray) {
                idUsuarios.add(Long.parseLong(idUsuario));
            }

            List<Usuario> usuarios = new ArrayList<>();
            for (Long usuarioId : idUsuarios) {
                Usuario usuario = usuarioServiceImp.obtenerUsuarioPorId(usuarioId);
                usuarios.add(usuario);
            }

            equipo.setUsuarios(usuarios);

            Long liderId = Long.parseLong(request.get("idLiderEquipo").toString());
            Usuario liderEquipo = usuarioServiceImp.obtenerUsuarioPorId(liderId);
            if (liderEquipo == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            equipo.setLider(liderEquipo);
            this.equipoServiceImp.actualizarEquipo(equipo);
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
            Equipo equipo = equipoServiceImp.obtenerEquipoPorId(id);

            if (equipo == null){
                response.put("status", HttpStatus.NOT_FOUND);
                response.put("data", "Equipo no encontrado");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            equipoServiceImp.eliminarEquipo(equipo);
            response.put("status","succes");
            response.put("data","Equipo eliminado correctamente");

        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_GATEWAY);
            response.put("data", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}