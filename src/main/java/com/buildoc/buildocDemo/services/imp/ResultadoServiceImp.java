package com.buildoc.buildocDemo.services.imp;

import com.buildoc.buildocDemo.entities.ResultadoInspeccion;
import com.buildoc.buildocDemo.services.ResultadoInspeccionServices;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ResultadoServiceImp implements ResultadoInspeccionServices {
    @Override
    public List<ResultadoInspeccion> listarResultadoInspecciones() {
        return null;
    }

    @Override
    public ResultadoInspeccion crearResultadoInspeccion(ResultadoInspeccion resultadoInspeccion) {
        return null;
    }

    @Override
    public ResultadoInspeccion obtenerResultadoInspeccionPorId(Long id) {
        return null;
    }

    @Override
    public ResultadoInspeccion actualizarResultadoInspeccion(ResultadoInspeccion resultadoInspeccion) {
        return null;
    }

    @Override
    public void eliminarResultadoInspeccion(Long id) {

    }
}
