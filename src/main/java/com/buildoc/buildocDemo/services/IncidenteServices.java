package com.buildoc.buildocDemo.services;

import com.buildoc.buildocDemo.entities.Incidente;

import java.util.List;

public interface IncidenteServices {
    public List<Incidente> listarIncidentes();

    Incidente crearIncidente(Incidente incidente);

    Incidente obtenerIncidentePorId(Long id);

    Incidente actualizarIncidente(Incidente incidente);

    void eliminarIncidente(Long id);
}
