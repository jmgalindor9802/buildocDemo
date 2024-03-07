package com.buildoc.buildocDemo.services;

import com.buildoc.buildocDemo.entities.ResultadoInspeccion;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ResultadoInspeccionServices {
    public List<ResultadoInspeccion> listarResultadoInspecciones();

    ResultadoInspeccion crearResultadoInspeccion(ResultadoInspeccion resultadoInspeccion);

    ResultadoInspeccion obtenerResultadoInspeccionPorId(Long id);

    ResultadoInspeccion actualizarResultadoInspeccion(ResultadoInspeccion resultadoInspeccion);

    void eliminarResultadoInspeccion(Long id);
}