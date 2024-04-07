package com.buildoc.buildocDemo.repositories;

import com.buildoc.buildocDemo.entities.Archivo;
import com.buildoc.buildocDemo.entities.Proyecto;
import com.buildoc.buildocDemo.entities.SeguimientoIncidente;
import com.buildoc.buildocDemo.entities.enums.EstadoDato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeguimientoIncidenteRepository extends JpaRepository<SeguimientoIncidente, Long> {
    List<SeguimientoIncidente> findByEstadoDato(EstadoDato estadoDato);
}
