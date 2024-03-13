package com.buildoc.buildocDemo.controller;
import com.buildoc.buildocDemo.entities.Cliente;
import com.buildoc.buildocDemo.entities.Persona;
import com.buildoc.buildocDemo.entities.Proyecto;
import com.buildoc.buildocDemo.services.imp.ClienteServiceImp;
import com.buildoc.buildocDemo.services.imp.ProyectoServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping(path = "/api/proyecto/",method = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.HEAD})
@CrossOrigin("*")
public class ProyectoController {
    @Autowired
    private ProyectoServiceImp proyectoServicesImp;
    @Autowired
    private ClienteServiceImp clienteServiceImp;
    @PostMapping("create")
    public ResponseEntity<Map<String, Object>> create(@RequestBody Map<String,Object>request){
        Map<String,Object> response = new HashMap<>();
        try {
            Proyecto proyecto = new Proyecto();
            proyecto.setNombre(request.get("nombre").toString());
            proyecto.setDireccion(request.get("direccion").toString());
            proyecto.setMunicipio(request.get("municipio").toString());
            proyecto.setDescripcion(request.get("descripcion").toString());
            LocalDateTime fechaActual = LocalDateTime.now();
            proyecto.setFechacreacion(fechaActual);

            Long idCliente = Long.parseLong(request.get("idCliente").toString());
            Cliente cliente = clienteServiceImp.obtenerClientePorId(idCliente);
            if (cliente == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            proyecto.setCliente(cliente);

            this.proyectoServicesImp.crearProyecto(proyecto);
            response.put("status","succes");
            response.put("data","Registro exitoso");

        }catch (Exception e){
            response.put("status",HttpStatus.BAD_GATEWAY);
            response.put("data",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("all")
    public ResponseEntity<Map<String, Object>> findAll(){
        Map<String,Object> response = new HashMap<>();
        try {
            List<Proyecto> proyectosList = this.proyectoServicesImp.listarProyectos();
            response.put("status","succes");
            response.put("data", proyectosList);

        }catch (Exception e){
            response.put("status",HttpStatus.BAD_GATEWAY);
            response.put("data",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}