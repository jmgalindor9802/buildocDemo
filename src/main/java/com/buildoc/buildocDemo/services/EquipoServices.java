package com.buildoc.buildocDemo.services;

import com.buildoc.buildocDemo.entities.Equipo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EquipoServices {
    public List<Equipo> listarEquipos();

    public void crearEquipo(Equipo equipo);

    public Equipo obtenerEquipoPorId(Long id);

    public void actualizarEquipo(Equipo equipo);

    public void eliminarEquipo(Equipo equipo);
}
