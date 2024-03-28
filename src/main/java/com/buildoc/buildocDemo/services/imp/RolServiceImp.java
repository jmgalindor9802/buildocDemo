package com.buildoc.buildocDemo.services.imp;
import com.buildoc.buildocDemo.entities.Rol;
import com.buildoc.buildocDemo.repositories.RolRepository;
import com.buildoc.buildocDemo.services.RolServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RolServiceImp implements RolServices {
    @Autowired
    private RolRepository rolRepository;
    @Override
    public List<Rol> listarRoles() {
        return rolRepository.findAll();
    }
    @Override
    public void crearRol(Rol rol) {
        rolRepository.save(rol);
    }
    @Override
    public Rol obtenerRolPorId(Integer id) {
        return rolRepository.getById(id);
    }
    @Override
    public void actualizarRol(Rol rol) {
       rolRepository.save(rol);
    }
    @Override
    public void eliminarRol(Rol rol) {
        rolRepository.delete(rol);
    }
}
