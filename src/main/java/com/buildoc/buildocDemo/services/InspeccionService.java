package com.buildoc.buildocDemo.services;

import com.buildoc.buildocDemo.entities.Inspeccion;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface InspeccionService {
    public List<Inspeccion> listarIncidentes();

    public void crearInspeccion(Inspeccion inspeccion);

    public Inspeccion obtenerInspeccionPorId(Long id);

    public void actualizarInspeccion(Inspeccion inspeccion);

    public void eliminarInspeccion(Inspeccion inspeccion);
}
