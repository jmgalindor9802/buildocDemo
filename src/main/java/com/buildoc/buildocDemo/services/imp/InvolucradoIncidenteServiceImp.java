package com.buildoc.buildocDemo.services.imp;

import com.buildoc.buildocDemo.entities.InvolucradoIncidente;
import com.buildoc.buildocDemo.services.InvolucradoIncidenteServices;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class InvolucradoIncidenteServiceImp implements InvolucradoIncidenteServices {
    @Override
    public List<InvolucradoIncidente> listarInvolucradosIncidentes() {
        return null;
    }

    @Override
    public InvolucradoIncidente crearInvolucradoIncidente(InvolucradoIncidente involucradoIncidente) {
        return null;
    }

    @Override
    public InvolucradoIncidente obtenerInvolucradoIncidentePorId(Long id) {
        return null;
    }

    @Override
    public InvolucradoIncidente actualizarInvolucradoIncidente(InvolucradoIncidente involucradoIncidente) {
        return null;
    }

    @Override
    public void eliminarInvolucradoIncidente(Long id) {

    }
}
