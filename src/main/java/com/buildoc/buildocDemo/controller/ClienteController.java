package com.buildoc.buildocDemo.controller;

import com.buildoc.buildocDemo.entities.Ciclo;
import com.buildoc.buildocDemo.entities.Cliente;
import com.buildoc.buildocDemo.entities.enums.EstadoDato;
import com.buildoc.buildocDemo.services.imp.ClienteServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/buildoc/cliente/",method = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,
        RequestMethod.HEAD})
@CrossOrigin("*")
public class ClienteController {
    @Autowired
    private ClienteServiceImp clienteServiceImp;
    @PostMapping("create")
    public ResponseEntity<Map<String, Object>> create(@RequestBody Map<String,Object>request){
        Map<String, Object> response = new HashMap<>();
        try {
            Cliente cliente = new Cliente();
            cliente.setNombre(request.get("nombre").toString());
            cliente.setCorreo(request.get("correo").toString());
            cliente.setTelefono(request.get("telefono").toString());
            cliente.setEstadoDato(EstadoDato.ACTIVO);
            this.clienteServiceImp.crearCliente(cliente);
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
            List<Cliente> clienteList = this.clienteServiceImp.listarEntidadesActivas();
            response.put("status","succes");
            response.put("data", clienteList);

        }catch (Exception e){
            response.put("status",HttpStatus.BAD_GATEWAY);
            response.put("data",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable Long id,@RequestBody Map<String,Object>request){
        Map<String, Object> response = new HashMap<>();
        try {
            Cliente cliente = clienteServiceImp.obtenerClientePorId(id);

            if (cliente == null){
                response.put("status", HttpStatus.NOT_FOUND);
                response.put("data", "Cliente no encontrado");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            cliente.setNombre(request.get("nombre").toString());
            cliente.setCorreo(request.get("correo").toString());
            cliente.setTelefono(request.get("telefono").toString());

            this.clienteServiceImp.actualizarCliente(cliente);
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
            Cliente cliente = clienteServiceImp.obtenerClientePorId(id);
            if (cliente == null){
                response.put("status", HttpStatus.NOT_FOUND);
                response.put("data", "Cliente no encontrado");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            clienteServiceImp.cambiarEstadoDato(id,EstadoDato.DESACTIVADO);
            response.put("status", "success");
            response.put("data", "Cliente eliminado exitosamente");
        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_GATEWAY);
            response.put("data", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
