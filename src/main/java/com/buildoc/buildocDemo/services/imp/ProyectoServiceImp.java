package com.buildoc.buildocDemo.services.imp;

import com.buildoc.buildocDemo.entities.Proyecto;
import com.buildoc.buildocDemo.services.ProyectoServices;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProyectoServiceImp implements ProyectoServices {
    @Override
    public List<Proyecto> listarProyectos() {
        return null;
    }

    @Override
    public Proyecto crearProyecto(Proyecto proyecto) {
        return null;
    }

    @Override
    public Proyecto obtenerProyectoPorId(Long id) {
        return null;
    }

    @Override
    public Proyecto actualizarProyecto(Proyecto proyecto) {
        return null;
    }

    @Override
    public void eliminarProyecto(Long id) {

    }
}
