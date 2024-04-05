package com.buildoc.buildocDemo.services.imp;

import com.buildoc.buildocDemo.entities.Tarea;
import com.buildoc.buildocDemo.entities.TipoInspeccion;
import com.buildoc.buildocDemo.entities.enums.EstadoDato;
import com.buildoc.buildocDemo.repositories.TipoInspeccionRepository;
import com.buildoc.buildocDemo.services.EntityService;
import com.buildoc.buildocDemo.services.TipoInspeccionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
@Service
public class TipoInspeccionServicesImp implements TipoInspeccionServices, EntityService {
    @Autowired
private TipoInspeccionRepository tipoInspeccionRepository;

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
        tipoInspeccionRepository.save(tipoInspeccion);

    }

    @Override
    public void eliminarTipoInspeccion(TipoInspeccion tipoInspeccion) {
        tipoInspeccionRepository.delete(tipoInspeccion);

    }

    @Override
    public void cambiarEstadoDato(Long id, EstadoDato estado) {
        TipoInspeccion tipoInspeccion = tipoInspeccionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Tipo de Inspección no encontrado"));
        tipoInspeccion.setEstadoDato(estado);
        tipoInspeccionRepository.save(tipoInspeccion);
    }

    @Override
    public List listarEntidadesActivas() {
        return tipoInspeccionRepository.findByEstadoDato(EstadoDato.ACTIVO);
    }
}
