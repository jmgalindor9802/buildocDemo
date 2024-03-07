package com.buildoc.buildocDemo.services;

import com.buildoc.buildocDemo.entities.Archivo;
import com.buildoc.buildocDemo.entities.TipoInspeccion;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface TipoInspeccionServices {
    public List<TipoInspeccion> listarTipoInspecciones();

    public void crearTipoInspeccion(TipoInspeccion tipoInspeccion);

    public TipoInspeccion obtenerTipoInspeccionPorId(Long id);

    public void actualizarTipoInspeccion(TipoInspeccion tipoInspeccion);

    public void eliminarTipoInspeccion(TipoInspeccion tipoInspeccion);
}
