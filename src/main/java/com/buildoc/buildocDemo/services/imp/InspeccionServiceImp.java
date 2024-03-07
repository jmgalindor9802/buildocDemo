package com.buildoc.buildocDemo.services.imp;

import com.buildoc.buildocDemo.entities.Inspeccion;
import com.buildoc.buildocDemo.repositories.InspeccionRepository;
import com.buildoc.buildocDemo.services.InspeccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InspeccionServiceImp implements InspeccionService {
    @Autowired
    private InspeccionRepository inspeccionRepository;
    @Override
    public List<Inspeccion> listarIncidentes() {
        return inspeccionRepository.findAll();
    }

    @Override
    public Inspeccion crearInspeccion(Inspeccion inspeccion) {
        return inspeccionRepository.save(inspeccion);
    }

    @Override
    public Inspeccion obtenerInspeccionPorId(Long id) {
        return null;
    }

    @Override
    public Inspeccion actualizarInspeccion(Inspeccion inspeccion) {
        return null;
    }

    @Override
    public void eliminarInspeccion(Long id) {

    }
}
