package com.buildoc.buildocDemo.services;

import com.buildoc.buildocDemo.entities.Rol;
import com.buildoc.buildocDemo.entities.Tarea;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public interface TareaServices {
    public List<Tarea> listarTareas();

    public void crearTarea(Tarea tarea);

    public Tarea obtenerTareaPorId(Long id);

    public void actualizarTarea(Tarea tarea);

    public void eliminarTarea(Tarea tarea);
}
