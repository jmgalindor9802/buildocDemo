package com.buildoc.buildocDemo.services;

import com.buildoc.buildocDemo.entities.SeguimientoIncidente;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface SeguimientoIncidenteServices {
    public List<SeguimientoIncidente> listarSeguimientos();

    SeguimientoIncidente crearSeguimiento(SeguimientoIncidente seguimientoIncidente);

    SeguimientoIncidente obtenerSeguimientoPorId(Long id);

    SeguimientoIncidente actualizarSeguimiento(SeguimientoIncidente seguimientoIncidente);

    void eliminarSeguimiento(Long id);

    List<SeguimientoIncidente> listarSeguimientoIncidentes();

    void crearSeguimientoIncidente(SeguimientoIncidente seguimientoIncidente);

    SeguimientoIncidente obtenerSeguimientoIncidentePorId(Long id);

    void actualizarSeguimientoIncidente(SeguimientoIncidente seguimientoIncidente);

    void eliminarArchivo(SeguimientoIncidente seguimientoIncidente);
}
