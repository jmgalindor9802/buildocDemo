package com.buildoc.buildocDemo.services.imp;

import com.buildoc.buildocDemo.entities.ResultadoInspeccion;
import com.buildoc.buildocDemo.repositories.ResultadoInspeccionRepository;
import com.buildoc.buildocDemo.services.ResultadoInspeccionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ResultadoServiceImp implements ResultadoInspeccionServices {
    @Autowired
    private ResultadoInspeccionRepository resultadoInspeccionRepository;
    @Override
    public List<ResultadoInspeccion> listarResultadoInspecciones() {
        return resultadoInspeccionRepository.findAll();
    }

    @Override
    public void crearResultadoInspeccion(ResultadoInspeccion resultadoInspeccion) {
        resultadoInspeccionRepository.save(resultadoInspeccion);
    }

    @Override
    public void obtenerResultadoInspeccionPorId(Long id) {
         resultadoInspeccionRepository.getById(id);
    }

    @Override
    public ResultadoInspeccion actualizarResultadoInspeccion(ResultadoInspeccion resultadoInspeccion) {
        return resultadoInspeccionRepository.save(resultadoInspeccion);
    }

    @Override
    public void eliminarResultadoInspeccion(Long id) {

    }
}
