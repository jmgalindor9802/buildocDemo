package com.buildoc.buildocDemo.services.imp;

import com.buildoc.buildocDemo.entities.Equipo;
import com.buildoc.buildocDemo.repositories.EquipoRepository;
import com.buildoc.buildocDemo.services.EquipoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipoServiceImp implements EquipoServices {

    @Autowired
    private EquipoRepository equipoRepository;
    @Override
    public List<Equipo> listarEquipos() {
        return equipoRepository.findAll();
    }

    @Override
    public Equipo crearEquipo(Equipo equipo) {
        return equipoRepository.save(equipo);
    }

    @Override
    public Equipo obtenerEquipoPorId(Long id) {
        return null;
    }

    @Override
    public Equipo actualizarEquipo(Equipo equipo) {
        return null;
    }

    @Override
    public void eliminarEquipo(Long id) {

    }


}
