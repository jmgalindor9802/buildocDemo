package com.buildoc.buildocDemo.services.imp;

import com.buildoc.buildocDemo.entities.Ciclo;
import com.buildoc.buildocDemo.entities.Proyecto;
import com.buildoc.buildocDemo.entities.Tarea;
import com.buildoc.buildocDemo.entities.enums.EstadoDato;
import com.buildoc.buildocDemo.repositories.CicloRepository;
import com.buildoc.buildocDemo.services.CicloServices;
import com.buildoc.buildocDemo.services.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CicloServiceImp implements CicloServices, EntityService {
    @Autowired
    private CicloRepository cicloRepository;

    @Override
    public List<Ciclo> listarCiclos() {
        return cicloRepository.findAll();
    }

    @Override
    public Long crearCiclo(Ciclo ciclo) {

        cicloRepository.save(ciclo);
        Ciclo cicloCreado=cicloRepository.save(ciclo);
        return cicloCreado.getIdCiclo();
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

    @Override
    public void cambiarEstadoDato(Long id, EstadoDato estado) {
        Ciclo ciclo = cicloRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Ciclo no encontrado"));
        ciclo.setEstadoDato(estado);
        cicloRepository.save(ciclo);
    }

    @Override
    public List listarEntidadesActivas() {
        return cicloRepository.findByEstadoDato(EstadoDato.ACTIVO);
    }
}