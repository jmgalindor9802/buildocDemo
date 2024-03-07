package com.buildoc.buildocDemo.services.imp;

import com.buildoc.buildocDemo.entities.TipoInspeccion;
import com.buildoc.buildocDemo.repositories.TipoInspeccionRepository;
import com.buildoc.buildocDemo.services.TipoInspeccionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TipoInspeccionServicesImp implements TipoInspeccionServices {
public TipoInspeccionRepository tipoInspeccionRepository;
    @Autowired

    @Override
    public List<TipoInspeccion> listarTipoInspecciones() {
        return tipoInspeccionRepository.findAll();
    }
    @Override
    public void crearTipoInspeccion(TipoInspeccion tipoInspeccion) {
    tipoInspeccionRepository.save(tipoInspeccion);
    }
    @Override
    public TipoInspeccion obtenerTipoInspeccionPorId(Long id) {
        return tipoInspeccionRepository.getById(id);
    }
    @Override
    public void actualizarTipoInspeccion(TipoInspeccion tipoInspeccion) {
        tipoInspeccionRepository.save(tipoInspeccion)

    }
    @Override
    public void eliminarTipoInspeccion(TipoInspeccion tipoInspeccion) {
tipoInspeccionRepository.delete();
    }
}
