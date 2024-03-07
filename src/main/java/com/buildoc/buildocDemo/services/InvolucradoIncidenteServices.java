package com.buildoc.buildocDemo.services;

import com.buildoc.buildocDemo.entities.InvolucradoIncidente;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface InvolucradoIncidenteServices {
    public List<InvolucradoIncidente> listarInvolucradosIncidentes();

    public void crearInvolucradoIncidente(InvolucradoIncidente involucradoIncidente);

    public InvolucradoIncidente obtenerInvolucradoIncidentePorId(Long id);

    public void actualizarInvolucradoIncidente(InvolucradoIncidente involucradoIncidente);

    public void eliminarInvolucradoIncidente(InvolucradoIncidente involucradoIncidente);
}
