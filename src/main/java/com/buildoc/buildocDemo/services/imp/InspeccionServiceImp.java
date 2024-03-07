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


}
