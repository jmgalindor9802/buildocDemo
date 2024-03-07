package com.buildoc.buildocDemo.services;

import com.buildoc.buildocDemo.entities.Proyecto;
import com.buildoc.buildocDemo.entities.ResultadoInspeccion;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ResultadoInspeccionServices {
    public List<ResultadoInspeccion> listarResultadosInspeccion();

    public void crearResultadoInspeccion(ResultadoInspeccion resultadoInspeccion);

    public ResultadoInspeccion obtenerResultadoInspeccionPorId(Long id);

    public void actualizarResultadoInspeccion(ResultadoInspeccion resultadoInspeccion);

    public void eliminarResultadoInspeccion(ResultadoInspeccion resultadoInspeccion);
}
