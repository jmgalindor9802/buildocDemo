package com.buildoc.buildocDemo.repositories;

import com.buildoc.buildocDemo.entities.Ciclo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CicloRepository extends JpaRepository<Ciclo,Long> {
    List<Ciclo> findByProyectoId(Long proyectoId);
}
