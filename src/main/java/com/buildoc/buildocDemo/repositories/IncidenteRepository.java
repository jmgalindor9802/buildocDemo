package com.buildoc.buildocDemo.repositories;

import com.buildoc.buildocDemo.entities.Incidente;
import com.buildoc.buildocDemo.entities.Tarea;
import com.buildoc.buildocDemo.entities.enums.EstadoDato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncidenteRepository extends JpaRepository<Incidente, Long> {
    List<Incidente> findByEstadoDato(EstadoDato estadoDato);
}
