package com.buildoc.buildocDemo.controller;

import com.buildoc.buildocDemo.entities.*;
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

@RestController
@RequestMapping(path = "/api/usuario/",method = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.HEAD})
@CrossOrigin("*")
public class UsuarioController {
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
            usuario.setEmail(request.get("email").toString());
            usuario.setContraseña(request.get("contraseña").toString());
            List<Long> idRoles = (List<Long>) request.get("idRoles");
            List<Rol> roles = new ArrayList<>();
            for (Long roleId : idRoles) {
                Rol rol = rolServiceImp.obtenerRolPorId(roleId);
                roles.add(rol);
            }
            usuario.setRoles(roles);
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
            List<Usuario> usuarioList = this.usuarioServiceImp.listarUsuarios();
            response.put("status", "succes");
            response.put("data", usuarioList);

        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_GATEWAY);
            response.put("data", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}
