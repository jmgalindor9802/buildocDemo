package com.buildoc.buildocDemo.services;

import com.buildoc.buildocDemo.entities.Incidente;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface IncidenteServices {
    public List<Incidente> listarIncidentes();

    Incidente crearIncidente(Incidente incidente);

    Incidente obtenerIncidentePorId(Long id);

    Incidente actualizarIncidente(Incidente incidente);

    void eliminarIncidente(Long id);
}
