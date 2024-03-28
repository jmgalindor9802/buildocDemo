package com.buildoc.buildocDemo.repositories;

import com.buildoc.buildocDemo.entities.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
    Optional<Rol>findByNombre(String nombre);

}
