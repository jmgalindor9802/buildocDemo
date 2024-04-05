package com.buildoc.buildocDemo.services.imp;

import com.buildoc.buildocDemo.entities.Incidente;
import com.buildoc.buildocDemo.entities.Inspeccion;
import com.buildoc.buildocDemo.entities.enums.EstadoDato;
import com.buildoc.buildocDemo.repositories.IncidenteRepository;
import com.buildoc.buildocDemo.services.EntityService;
import com.buildoc.buildocDemo.services.IncidenteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class IncidenteServiceImp implements IncidenteServices, EntityService {
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

    @Override
    public void cambiarEstadoDato(Long id, EstadoDato estado) {
        Incidente incidente = incidenteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Incidente no encontrado"));
        incidente.setEstadoDato(estado);
        incidenteRepository.save(incidente);
    }

    @Override
    public List listarEntidadesActivas() {
        return incidenteRepository.findByEstadoDato(EstadoDato.ACTIVO);
    }
}
