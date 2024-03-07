package com.buildoc.buildocDemo.services;

import com.buildoc.buildocDemo.entities.Seguimiento;

import java.util.List;

public interface SeguimientoServices {
    public List<Seguimiento> listarSeguimientos();

    Seguimiento crearSeguimiento(Seguimiento seguimiento);

    Seguimiento obtenerSeguimientoPorId(Long id);

    Seguimiento actualizarSeguimiento(Seguimiento seguimiento);

    void eliminarSeguimiento(Long id);
}
