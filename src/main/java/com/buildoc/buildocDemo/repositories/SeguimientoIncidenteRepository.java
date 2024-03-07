package com.buildoc.buildocDemo.repositories;

import com.buildoc.buildocDemo.entities.Archivo;
import com.buildoc.buildocDemo.entities.SeguimientoIncidente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeguimientoIncidenteRepository extends JpaRepository<SeguimientoIncidente, Long> {
}
