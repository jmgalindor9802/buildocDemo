package com.buildoc.buildocDemo.services;

import com.buildoc.buildocDemo.entities.Incidente;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface IncidenteServices {
    public List<Incidente> listarIncidentes();

    public void crearIncidente(Incidente incidente);

    public Incidente obtenerIncidentePorId(Long id);

    public void actualizarIncidente(Incidente incidente);

    public void eliminarIncidente(Incidente incidente);
}
