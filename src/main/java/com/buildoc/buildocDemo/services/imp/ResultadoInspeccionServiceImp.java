package com.buildoc.buildocDemo.services.imp;

import com.buildoc.buildocDemo.entities.Proyecto;
import com.buildoc.buildocDemo.entities.ResultadoInspeccion;
import com.buildoc.buildocDemo.repositories.ProyectoRepository;
import com.buildoc.buildocDemo.repositories.ResultadoInspeccionRepository;
import com.buildoc.buildocDemo.services.ResultadoInspeccionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ResultadoInspeccionServiceImp implements ResultadoInspeccionServices {
    @Autowired
    private ResultadoInspeccionRepository resultadoInspeccionRepository;
    @Override
    public List<ResultadoInspeccion> listarResultadosInspeccion() {
        return resultadoInspeccionRepository.findAll();
    }
    @Override
    public void crearResultadoInspeccion(ResultadoInspeccion resultadoInspeccion) {
        resultadoInspeccionRepository.save(resultadoInspeccion);
    }
    @Override
    public ResultadoInspeccion obtenerResultadoInspeccionPorId(Long id) {
        return resultadoInspeccionRepository.getById(id);
    }
    @Override
    public void actualizarResultadoInspeccion(ResultadoInspeccion resultadoInspeccion) {
        resultadoInspeccionRepository.save(resultadoInspeccion);
    }
    @Override
    public void eliminarResultadoInspeccion(ResultadoInspeccion resultadoInspeccion) {
        resultadoInspeccionRepository.delete(resultadoInspeccion);
    }
}
