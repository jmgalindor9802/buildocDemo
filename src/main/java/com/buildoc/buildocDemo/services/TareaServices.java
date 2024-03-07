package com.buildoc.buildocDemo.services;

import com.buildoc.buildocDemo.entities.Tarea;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public interface TareaServices {
    public List<Tarea> listarTareas();

    Tarea crearPersona(Tarea tarea);

    Tarea obtenerTareaPorId(Long id);

    Tarea actualizarTarea(Tarea tarea);

    void eliminarTarea(Long id);
}
