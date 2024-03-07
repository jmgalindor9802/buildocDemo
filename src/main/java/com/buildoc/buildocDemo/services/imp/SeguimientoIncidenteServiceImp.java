package com.buildoc.buildocDemo.services.imp;
import com.buildoc.buildocDemo.entities.SeguimientoIncidente;
import com.buildoc.buildocDemo.repositories.SeguimientoIncidenteRepository;
import com.buildoc.buildocDemo.services.SeguimientoIncidenteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SeguimientoIncidenteServiceImp implements SeguimientoIncidenteServices {
    @Autowired
    private SeguimientoIncidenteRepository seguimientoIncidenteRepository;
    @Override
    public List<SeguimientoIncidente> listarSeguimientoIncidentes() {
        return seguimientoIncidenteRepository.findAll();
    }
    @Override
    public void crearSeguimientoIncidente(SeguimientoIncidente seguimientoIncidente) {
        seguimientoIncidenteRepository.save(seguimientoIncidente);
    }
    @Override
    public SeguimientoIncidente obtenerSeguimientoIncidentePorId(Long id) {
        return seguimientoIncidenteRepository.getById(id);
    }
    @Override
    public void actualizarSeguimientoIncidente(SeguimientoIncidente seguimientoIncidente) {
        seguimientoIncidenteRepository.save(seguimientoIncidente);
    }

    @Override
    public void eliminarSeguimientoIncidente(SeguimientoIncidente seguimientoIncidente) {
        seguimientoIncidenteRepository.delete(seguimientoIncidente);
    }
}
