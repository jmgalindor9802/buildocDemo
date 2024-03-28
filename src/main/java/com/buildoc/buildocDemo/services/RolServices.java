package com.buildoc.buildocDemo.services;
import com.buildoc.buildocDemo.entities.Archivo;
import com.buildoc.buildocDemo.entities.Rol;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public interface RolServices {
    public List<Rol> listarRoles();

    public void crearRol(Rol rol);

    public Rol obtenerRolPorId(Integer id);

    public void actualizarRol(Rol rol);

    public void eliminarRol(Rol rol);
}
