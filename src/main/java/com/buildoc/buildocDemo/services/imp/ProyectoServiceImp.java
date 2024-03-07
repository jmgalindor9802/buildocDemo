package com.buildoc.buildocDemo.services.imp;
import com.buildoc.buildocDemo.entities.Proyecto;
import com.buildoc.buildocDemo.repositories.ProyectoRepository;
import com.buildoc.buildocDemo.services.ProyectoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProyectoServiceImp implements ProyectoServices {
    @Autowired
    private ProyectoRepository proyectoRepository;
    @Override
    public List<Proyecto> listarProyectos() {
        return proyectoRepository.findAll();
    }
    @Override
    public void crearProyecto(Proyecto proyecto) {
        proyectoRepository.save(proyecto);
    }
    @Override
    public Proyecto obtenerProyectoPorId(Long id) {
        return proyectoRepository.getById(id);
    }
    @Override
    public void actualizarProyecto(Proyecto proyecto) {
        proyectoRepository.save(proyecto);
    }
    @Override
    public void eliminarProyecto(Proyecto proyecto) {
        proyectoRepository.delete(proyecto);
    }
}
