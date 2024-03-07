package com.buildoc.buildocDemo.services.imp;

import com.buildoc.buildocDemo.entities.Tarea;
import com.buildoc.buildocDemo.services.TareaServices;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TareaServiceImp implements TareaServices {
    @Override
    public List<Tarea> listarTareas() {
        return null;
    }

    @Override
    public Tarea crearPersona(Tarea tarea) {
        return null;
    }

    @Override
    public Tarea obtenerTareaPorId(Long id) {
        return null;
    }

    @Override
    public Tarea actualizarTarea(Tarea tarea) {
        return null;
    }

    @Override
    public void eliminarTarea(Long id) {

    }
}
