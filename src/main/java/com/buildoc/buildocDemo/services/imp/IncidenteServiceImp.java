package com.buildoc.buildocDemo.services.imp;

import com.buildoc.buildocDemo.entities.Incidente;
import com.buildoc.buildocDemo.repositories.IncidenteRepository;
import com.buildoc.buildocDemo.services.IncidenteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncidenteServiceImp implements IncidenteServices {
    @Autowired
    private IncidenteRepository incidenteRepository;
    @Override
    public List<Incidente> listarIncidentes() {
        return incidenteRepository.findAll();
    }

    @Override
    public Incidente crearIncidente(Incidente incidente) {
        return incidenteRepository.save(incidente);
    }

    @Override
    public Incidente obtenerIncidentePorId(Long id) {
        return null;
    }

    @Override
    public Incidente actualizarIncidente(Incidente incidente) {
        return null;
    }

    @Override
    public void eliminarIncidente(Long id) {

    }
}
