package com.buildoc.buildocDemo.services;

import com.buildoc.buildocDemo.entities.RespuestaTarea;
import com.buildoc.buildocDemo.entities.Tarea;
import com.buildoc.buildocDemo.entities.Usuario;
import com.buildoc.buildocDemo.repositories.RespuestaTareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface RespuestaTareaService {
    RespuestaTarea crearRespuestaTarea(RespuestaTarea respuestaTarea);

    RespuestaTarea obtenerRespuestaTareaPorId(Long idRespuestaTarea);

    List<RespuestaTarea> obtenerRespuestasTareaPorTarea(Long idTarea);

    List<RespuestaTarea> obtenerRespuestasTareaPorUsuario(Usuario usuario);

    RespuestaTarea actualizarRespuestaTarea(RespuestaTarea respuestaTarea);

    void eliminarRespuestaTarea(Long idRespuestaTarea);
}
