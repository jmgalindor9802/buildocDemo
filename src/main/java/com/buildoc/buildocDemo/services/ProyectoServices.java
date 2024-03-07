package com.buildoc.buildocDemo.services;

import com.buildoc.buildocDemo.entities.Proyecto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ProyectoServices {
    public List<Proyecto> listarProyectos();

    Proyecto crearProyecto(Proyecto proyecto);

    Proyecto obtenerProyectoPorId(Long id);

   Proyecto actualizarProyecto(Proyecto proyecto);

    void eliminarProyecto(Long id);
}
