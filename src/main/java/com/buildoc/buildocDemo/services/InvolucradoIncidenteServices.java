package com.buildoc.buildocDemo.services;

import com.buildoc.buildocDemo.entities.InvolucradoIncidente;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface InvolucradoIncidenteServices {
    public List<InvolucradoIncidente> listarInvolucradosIncidentes();

    InvolucradoIncidente crearInvolucradoIncidente(InvolucradoIncidente involucradoIncidente);

    InvolucradoIncidente obtenerInvolucradoIncidentePorId(Long id);

    InvolucradoIncidente actualizarInvolucradoIncidente(InvolucradoIncidente involucradoIncidente);

    void eliminarInvolucradoIncidente(Long id);
}
