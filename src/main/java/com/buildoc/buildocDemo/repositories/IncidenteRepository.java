package com.buildoc.buildocDemo.repositories;

import com.buildoc.buildocDemo.entities.Incidente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncidenteRepository extends JpaRepository<Incidente, Long> {
}
