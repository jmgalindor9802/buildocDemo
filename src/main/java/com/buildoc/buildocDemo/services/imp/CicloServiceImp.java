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
    public Ciclo crearCiclo(Ciclo ciclo) {
        return cicloRepository.save(ciclo);
    }

    @Override
    public Ciclo obtenerCicloPorId(Long id) {
        return null;
    }

    @Override
    public Ciclo actualizarCiclo(Ciclo ciclo) {
        return null;
    }

    @Override
    public void eliminarCiclo(Long id) {

    }
}
