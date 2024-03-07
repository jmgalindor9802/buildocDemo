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
    public void crearIncidente(Incidente incidente) {
        incidenteRepository.save(incidente);
    }

    @Override
    public Incidente obtenerIncidentePorId(Long id) {
        return incidenteRepository.getById(id);
    }

    @Override
    public void actualizarIncidente(Incidente incidente) {
        incidenteRepository.save(incidente);
    }

    @Override
    public void eliminarIncidente(Incidente incidente) {
        incidenteRepository.delete(incidente);
    }
}
