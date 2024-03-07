package com.buildoc.buildocDemo.services.imp;

import com.buildoc.buildocDemo.entities.Rol;
import com.buildoc.buildocDemo.entities.Tarea;
import com.buildoc.buildocDemo.repositories.ArchivoRepository;
import com.buildoc.buildocDemo.repositories.TareaRepository;
import com.buildoc.buildocDemo.services.TareaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TareaServiceImp implements TareaServices {
    @Autowired
    private TareaRepository tareaRepository;
    @Override
    public List<Tarea> listarTareas() {
        return tareaRepository.findAll();
    }

    @Override
    public void crearTarea(Tarea tarea) {
        tareaRepository.save(tarea);
    }

    @Override
    public Tarea obtenerTareaPorId(Long id) {
        return tareaRepository.getById(id);
    }

    @Override
    public void actualizarTarea(Tarea tarea) {
        tareaRepository.save(tarea);
    }

    @Override
    public void eliminarTarea(Tarea tarea) {
        tareaRepository.delete(tarea);
    }
}
