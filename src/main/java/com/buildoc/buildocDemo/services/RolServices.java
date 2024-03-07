package com.buildoc.buildocDemo.services;
import com.buildoc.buildocDemo.entities.Rol;


import java.util.List;

public interface RolServices {

    public List<Rol> listarRoles();

    Rol crearRol(Rol rol);

    Rol obtenerRolPorId(Long id);

    Rol actualizarRol(Rol rol);

    void eliminarRol(Long id);
}
