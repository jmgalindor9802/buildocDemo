package com.buildoc.buildocDemo.services.imp;

import com.buildoc.buildocDemo.entities.TipoInspeccion;
import com.buildoc.buildocDemo.services.TipoInspeccionServices;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TipoInspeccionServicesImp implements TipoInspeccionServices {
    @Override
    public List<TipoInspeccion> listarTareas() {
        return null;
    }

    @Override
    public TipoInspeccion crearTipoInspeccion(TipoInspeccion tipoInspeccion) {
        return null;
    }

    @Override
    public TipoInspeccion obtenerTipoInspeccionPorId(Long id) {
        return null;
    }

    @Override
    public TipoInspeccion actualizarTipoInspeccion(TipoInspeccion tipoInspeccion) {
        return null;
    }

    @Override
    public void eliminarTipoInspeccion(Long id) {

    }
}
