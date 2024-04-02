package com.buildoc.buildocDemo.services;

import com.buildoc.buildocDemo.entities.Ciclo;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CicloServices {
    public List<Ciclo> listarCiclos();

   public Long crearCiclo(Ciclo ciclo);

   public Ciclo obtenerCicloPorId(Long id);

    public void actualizarCiclo(Ciclo ciclo);

    public void eliminarCiclo(Ciclo ciclo);

    List<Ciclo> listarCiclosPorProyecto(Long proyectoId);
}
