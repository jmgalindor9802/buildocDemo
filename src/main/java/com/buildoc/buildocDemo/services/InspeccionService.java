package com.buildoc.buildocDemo.services;

import com.buildoc.buildocDemo.entities.Inspeccion;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface InspeccionService {
    public List<Inspeccion> listarIncidentes();

    Inspeccion crearInspeccion(Inspeccion inspeccion);

    Inspeccion obtenerInspeccionPorId(Long id);

    Inspeccion actualizarInspeccion(Inspeccion inspeccion);

    void eliminarInspeccion(Long id);
}
