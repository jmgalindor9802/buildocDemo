package com.buildoc.buildocDemo.repositories;

import com.buildoc.buildocDemo.entities.Usuario;
import com.buildoc.buildocDemo.entities.enums.EstadoDato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario>findByUsername(String username);
    Boolean existsByUsername(String username);

    List<Usuario> findByEstadoDato(EstadoDato estadoDato);
}
