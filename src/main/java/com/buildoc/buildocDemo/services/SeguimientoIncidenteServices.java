package com.buildoc.buildocDemo.services;

import com.buildoc.buildocDemo.entities.SeguimientoIncidente;
import com.buildoc.buildocDemo.entities.Tarea;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface SeguimientoIncidenteServices {

    public List<SeguimientoIncidente> listarSeguimientoIncidentes();

    public void crearSeguimientoIncidente(SeguimientoIncidente seguimientoIncidente);

    public SeguimientoIncidente obtenerSeguimientoIncidentePorId(Long id);

    public void actualizarSeguimientoIncidente(SeguimientoIncidente seguimientoIncidente);

    public void eliminarSeguimientoIncidente(SeguimientoIncidente seguimientoIncidente);
}
