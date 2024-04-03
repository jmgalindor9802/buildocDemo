package com.buildoc.buildocDemo.services.imp;

import com.buildoc.buildocDemo.entities.RespuestaTarea;
import com.buildoc.buildocDemo.entities.Tarea;
import com.buildoc.buildocDemo.entities.Usuario;
import com.buildoc.buildocDemo.repositories.RespuestaTareaRepository;
import com.buildoc.buildocDemo.services.RespuestaTareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
@Service
public class RespuestaTareaServiceImp implements RespuestaTareaService {
    @Autowired
    private RespuestaTareaRepository respuestaTareaRepository;
    @Autowired
    private TareaServiceImp tareaServiceImp;

    @Override
    public RespuestaTarea crearRespuestaTarea(RespuestaTarea respuestaTarea) {
        return respuestaTareaRepository.save(respuestaTarea);
    }

    @Override
    public RespuestaTarea obtenerRespuestaTareaPorId(Long idRespuestaTarea) {
        return respuestaTareaRepository.findById(idRespuestaTarea).orElse(null);
    }

    @Override
    public List<RespuestaTarea> obtenerRespuestasTareaPorTarea(Long idTarea) {
        // Obtener la tarea correspondiente al ID
        Tarea tarea = tareaServiceImp.obtenerTareaPorId(idTarea);
        if (tarea == null) {
            // Manejar el caso en que no se encuentre la tarea
            // Puedes lanzar una excepción, devolver una lista vacía o hacer cualquier otra acción según tu requerimiento
            return Collections.emptyList(); // Devolver una lista vacía en este caso
        }

        // Utilizar la tarea para obtener las respuestas de tarea
        return respuestaTareaRepository.findByTarea(tarea);
    }


    @Override
    public List<RespuestaTarea> obtenerRespuestasTareaPorUsuario(Usuario usuario) {
        return respuestaTareaRepository.findByUsuario(usuario);
    }

    @Override
    public RespuestaTarea actualizarRespuestaTarea(RespuestaTarea respuestaTarea) {
        return respuestaTareaRepository.save(respuestaTarea);
    }

    @Override
    public void eliminarRespuestaTarea(Long idRespuestaTarea) {
        respuestaTareaRepository.deleteById(idRespuestaTarea);
    }
}
