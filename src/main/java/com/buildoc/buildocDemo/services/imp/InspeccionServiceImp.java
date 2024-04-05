package com.buildoc.buildocDemo.services.imp;

import com.buildoc.buildocDemo.entities.Inspeccion;
import com.buildoc.buildocDemo.entities.TipoInspeccion;
import com.buildoc.buildocDemo.entities.enums.EstadoDato;
import com.buildoc.buildocDemo.repositories.InspeccionRepository;
import com.buildoc.buildocDemo.services.EntityService;
import com.buildoc.buildocDemo.services.InspeccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class InspeccionServiceImp implements InspeccionService, EntityService {
    @Autowired
    private InspeccionRepository inspeccionRepository;
    @Override
    public List<Inspeccion> listarIncidentes() {
        return inspeccionRepository.findAll();
    }

    @Override
    public void crearInspeccion(Inspeccion inspeccion) {
        inspeccionRepository.save(inspeccion);
    }

    @Override
    public Inspeccion obtenerInspeccionPorId(Long id) {
        return inspeccionRepository.getById(id);
    }

    @Override
    public void actualizarInspeccion(Inspeccion inspeccion) {
        inspeccionRepository.save(inspeccion);
    }

    @Override
    public void eliminarInspeccion(Inspeccion inspeccion) {
        inspeccionRepository.delete(inspeccion);
    }


    @Override
    public void cambiarEstadoDato(Long id, EstadoDato estado) {
        Inspeccion inspeccion = inspeccionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Inspeccion no encontrado"));
        inspeccion.setEstadoDato(estado);
        inspeccionRepository.save(inspeccion);
    }

    @Override
    public List listarEntidadesActivas() {
        return inspeccionRepository.findByEstadoDato(EstadoDato.ACTIVO);
    }
}
