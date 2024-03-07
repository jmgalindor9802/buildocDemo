package com.buildoc.buildocDemo.services;

import com.buildoc.buildocDemo.entities.TipoInspeccion;

import java.util.List;

public interface TipoInspeccionServices {
    public List<TipoInspeccion> listarTareas();

    TipoInspeccion crearTipoInspeccion(TipoInspeccion tipoInspeccion);

    TipoInspeccion obtenerTipoInspeccionPorId(Long id);

    TipoInspeccion actualizarTipoInspeccion(TipoInspeccion tipoInspeccion);

    void eliminarTipoInspeccion(Long id);
}
