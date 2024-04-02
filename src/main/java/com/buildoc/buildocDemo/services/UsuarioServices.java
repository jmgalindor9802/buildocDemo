package com.buildoc.buildocDemo.services;

import com.buildoc.buildocDemo.entities.Archivo;
import com.buildoc.buildocDemo.entities.Persona;
import com.buildoc.buildocDemo.entities.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UsuarioServices {
    public List<Usuario> listarUsuarios();

    public void crearUsuario(Usuario usuario);

    public Usuario obtenerUsuarioPorId(Long id);

    public void actualizarUsuario(Usuario usuario);

    public void eliminarUsuario(Usuario usuario);

    public String obtenerUsuarioPorUsername(String username);


}
