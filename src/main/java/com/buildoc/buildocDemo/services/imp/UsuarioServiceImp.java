package com.buildoc.buildocDemo.services.imp;
import com.buildoc.buildocDemo.entities.Usuario;
import com.buildoc.buildocDemo.entities.enums.EstadoDato;
import com.buildoc.buildocDemo.repositories.UsuarioRepository;
import com.buildoc.buildocDemo.services.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.SpringVersion;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImp implements UsuarioServices {

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    public List<Usuario> listarUsuarios() {
       return usuarioRepository.findAll();
    }

    public List<Usuario> listarUsuariosActivos() {
        return usuarioRepository.findByEstado(EstadoDato.ACTIVO);
    }


    @Override
    public void crearUsuario(Usuario usuario) {
usuarioRepository.save(usuario);
    }

    @Override
    public Usuario obtenerUsuarioPorId(Long id) {
        return usuarioRepository.getById(id);
    }

    @Override
    public void actualizarUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);

    }

    @Override
    public void eliminarUsuario(Usuario usuario) {
        usuarioRepository.delete(usuario);

    }


    @Override
    public String obtenerUsuarioPorUsername(String username) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByUsername(username);
        return usuarioOptional.map(Usuario::getNombre).orElse(null);
    }
    @Override
    public Long obtenerIdUsuarioPorUsername(String username) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByUsername(username);
        return usuarioOptional.map(Usuario::getId).orElse(null);
    }


}
