package com.buildoc.buildocDemo.services.imp;

import com.buildoc.buildocDemo.entities.Ciclo;
import com.buildoc.buildocDemo.entities.Proyecto;
import com.buildoc.buildocDemo.entities.Rol;
import com.buildoc.buildocDemo.entities.Tarea;
import com.buildoc.buildocDemo.entities.enums.EstadoDato;
import com.buildoc.buildocDemo.repositories.ArchivoRepository;
import com.buildoc.buildocDemo.repositories.TareaRepository;
import com.buildoc.buildocDemo.services.EntityService;
import com.buildoc.buildocDemo.services.TareaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
@Service
public class TareaServiceImp implements TareaServices, EntityService {
    @Autowired
    private TareaRepository tareaRepository;
    @Override
    public List<Tarea> listarTareas() {
        return tareaRepository.findAll();
    }

    @Override
    public Long crearTarea(Tarea tarea) {
        tareaRepository.save(tarea);
        Tarea tareaCreada = tareaRepository.save(tarea);
        return tareaCreada.getIdTarea();
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

    @Override
    public void cambiarEstadoDato(Long id, EstadoDato estado) {
        Tarea tarea = tareaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Ciclo no encontrado"));
        tarea.setEstadoDato(estado);
        tareaRepository.save(tarea);
    }

    @Override
    public List listarEntidadesActivas() {
        return tareaRepository.findByEstadoDato(EstadoDato.ACTIVO);
    }
}
