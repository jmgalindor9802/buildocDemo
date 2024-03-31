package com.buildoc.buildocDemo.services.imp;

import com.buildoc.buildocDemo.entities.Ciclo;
import com.buildoc.buildocDemo.repositories.CicloRepository;
import com.buildoc.buildocDemo.services.CicloServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CicloServiceImp implements CicloServices {
    @Autowired
    private CicloRepository cicloRepository;

    @Override
    public List<Ciclo> listarCiclos() {
        return cicloRepository.findAll();
    }

    @Override
    public void crearCiclo(Ciclo ciclo) {
        cicloRepository.save(ciclo);
    }

    @Override
    public Ciclo obtenerCicloPorId(Long id) {
        return cicloRepository.getById(id);
    }

    @Override
    public void actualizarCiclo(Ciclo ciclo) {
        cicloRepository.save(ciclo);
    }

    @Override
    public void eliminarCiclo(Ciclo ciclo) {
        cicloRepository.delete(ciclo);
    }

    @Override
    public List<Ciclo> listarCiclosPorProyecto(Long proyectoId) {
        return cicloRepository.findByProyectoId(proyectoId);
    }
}