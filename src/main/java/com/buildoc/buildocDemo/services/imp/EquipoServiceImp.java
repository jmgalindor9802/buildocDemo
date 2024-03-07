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
    public void crearEquipo(Equipo equipo) {
         equipoRepository.save(equipo);
    }

    @Override
    public Equipo obtenerEquipoPorId(Long id) {
        return equipoRepository.getById(id);
    }

    @Override
    public void actualizarEquipo(Equipo equipo) {
        equipoRepository.save(equipo);
    }

    @Override
    public void eliminarEquipo(Equipo equipo) {
        equipoRepository.delete(equipo);
    }


}
