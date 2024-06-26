package com.buildoc.buildocDemo.services.imp;
import com.buildoc.buildocDemo.entities.Usuario;
import com.buildoc.buildocDemo.entities.enums.EstadoDato;
import com.buildoc.buildocDemo.repositories.UsuarioRepository;
import com.buildoc.buildocDemo.services.EntityService;
import com.buildoc.buildocDemo.services.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.SpringVersion;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImp implements UsuarioServices, EntityService<Usuario> {

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    public List<Usuario> listarUsuarios() {
       return usuarioRepository.findAll();
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


    @Override
    public void cambiarEstadoDato(Long id, EstadoDato estado) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
        usuario.setEstadoDato(estado);
        usuarioRepository.save(usuario);
    }

    @Override
    public List<Usuario> listarEntidadesActivas() {
        return usuarioRepository.findByEstadoDato(EstadoDato.ACTIVO);
    }
}
