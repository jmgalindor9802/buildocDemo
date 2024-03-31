package com.buildoc.buildocDemo.services;

import com.buildoc.buildocDemo.entities.Proyecto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ProyectoServices {
    public List<Proyecto> listarProyectos();

    public Long crearProyecto(Proyecto proyecto);

    public Proyecto obtenerProyectoPorId(Long id);

   public void actualizarProyecto(Proyecto proyecto);

   public void eliminarProyecto(Proyecto proyecto);
}
