package com.buildoc.buildocDemo.services.imp;

import com.buildoc.buildocDemo.entities.Seguimiento;
import com.buildoc.buildocDemo.services.SeguimientoServices;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SeguimientoServiceImp implements SeguimientoServices {
    @Override
    public List<Seguimiento> listarSeguimientos() {
        return null;
    }

    @Override
    public Seguimiento crearSeguimiento(Seguimiento seguimiento) {
        return null;
    }

    @Override
    public Seguimiento obtenerSeguimientoPorId(Long id) {
        return null;
    }

    @Override
    public Seguimiento actualizarSeguimiento(Seguimiento seguimiento) {
        return null;
    }

    @Override
    public void eliminarSeguimiento(Long id) {

    }
}
