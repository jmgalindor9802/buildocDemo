package com.buildoc.buildocDemo.services.imp;

import com.buildoc.buildocDemo.entities.InvolucradoIncidente;
import com.buildoc.buildocDemo.repositories.InvolucradoIncidenteRepository;
import com.buildoc.buildocDemo.services.InvolucradoIncidenteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class InvolucradoIncidenteServiceImp implements InvolucradoIncidenteServices {
    @Autowired
    private InvolucradoIncidenteRepository involucradoIncidenteRepository;
    @Override
    public List<InvolucradoIncidente> listarInvolucradosIncidentes() {
        return involucradoIncidenteRepository.findAll();
    }

    @Override
    public void crearInvolucradoIncidente(InvolucradoIncidente involucradoIncidente) {
        involucradoIncidenteRepository.save(involucradoIncidente);
    }

    @Override
    public InvolucradoIncidente obtenerInvolucradoIncidentePorId(Long id) {
        return involucradoIncidenteRepository.getById(id);
    }

    @Override
    public void actualizarInvolucradoIncidente(InvolucradoIncidente involucradoIncidente) {
        involucradoIncidenteRepository.save(involucradoIncidente);
    }

    @Override
    public void eliminarInvolucradoIncidente(InvolucradoIncidente involucradoIncidente) {
        involucradoIncidenteRepository.delete(involucradoIncidente);
    }


}
