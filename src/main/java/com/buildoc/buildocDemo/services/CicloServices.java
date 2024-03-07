package com.buildoc.buildocDemo.services;

import com.buildoc.buildocDemo.entities.Ciclo;

import java.util.List;

public interface CicloServices {
    public List<Ciclo> listarCiclos();

   Ciclo crearCiclo(Ciclo ciclo);

  Ciclo obtenerCicloPorId(Long id);

    Ciclo actualizarCiclo(Ciclo ciclo);

    void eliminarCiclo(Long id);
}
