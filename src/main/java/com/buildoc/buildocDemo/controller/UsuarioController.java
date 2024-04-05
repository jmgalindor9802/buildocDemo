package com.buildoc.buildocDemo.controller;

import com.buildoc.buildocDemo.dto.UsuarioDto;
import com.buildoc.buildocDemo.entities.*;
import com.buildoc.buildocDemo.entities.enums.EstadoDato;
import com.buildoc.buildocDemo.services.imp.PersonaServiceImp;
import com.buildoc.buildocDemo.services.imp.RolServiceImp;
import com.buildoc.buildocDemo.services.imp.UsuarioServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/buildoc/usuario/",method = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.HEAD})
@CrossOrigin("*")
public class  UsuarioController {
    @Autowired
    private UsuarioServiceImp usuarioServiceImp;
    @Autowired
    private PersonaServiceImp personaServiceImp;
    @Autowired
    private RolServiceImp rolServiceImp;
    @PostMapping("create")
    public ResponseEntity<Map<String, Object>> create(@RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        try {
            Usuario usuario = new Usuario();
            usuario.setUsername(request.get("username").toString());
            usuario.setPassword(request.get("password").toString());
            usuario.setEstadoDato(EstadoDato.ACTIVO);

            Long idRol = Long.parseLong(request.get("rolId").toString());
            Rol rol=rolServiceImp.obtenerRolPorId(idRol);
            usuario.setRol(rol);
            Long idPersona = Long.parseLong(request.get("idPersona").toString());
            Persona persona = personaServiceImp.obtenerPersonaPorId(idPersona);
            if (persona == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            usuario.setPersona(persona);
            this.usuarioServiceImp.crearUsuario(usuario);
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
    public ResponseEntity<Map<String, Object>> findAll() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Usuario> usuarioList = this.usuarioServiceImp.listarEntidadesActivas();
            response.put("status", "succes");
            response.put("data", usuarioList);

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
            Usuario usuario = usuarioServiceImp.obtenerUsuarioPorId(id);

            if (usuario == null){
                response.put("status", HttpStatus.NOT_FOUND);
                response.put("data", "Usuario no encontrado");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            usuarioServiceImp.cambiarEstadoDato(id, EstadoDato.DESACTIVADO);

            response.put("status", "success");
            response.put("data", "Usuario deshabilitado correctamente");

        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_GATEWAY);
            response.put("data", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Usuario usuario = usuarioServiceImp.obtenerUsuarioPorId(id);
            if (usuario == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            UsuarioDto usuarioDto = new UsuarioDto();
            usuarioDto.setNombre(usuario.getNombre());
            usuarioDto.setPersonaId(usuario.getPersona().getCedula());
            usuarioDto.setPassword(usuario.getPassword());
            usuarioDto.setUsername(usuario.getUsername());
            usuarioDto.setRol(usuario.getRol().getNombre());
            response.put("status", HttpStatus.OK);
            response.put("data", usuarioDto);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
            response.put("data", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("update/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable Long id, @RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        try {
            Usuario usuario = usuarioServiceImp.obtenerUsuarioPorId(id);
            if (usuario == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            usuario.setUsername(request.get("username").toString());
            this.usuarioServiceImp.actualizarUsuario(usuario);
            response.put("status", "success");
            response.put("data", "Usuario actualizado correctamente");
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_GATEWAY);
            response.put("data", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
    }

}
