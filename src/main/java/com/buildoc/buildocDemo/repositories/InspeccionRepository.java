package com.buildoc.buildocDemo.repositories;

import com.buildoc.buildocDemo.entities.Inspeccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InspeccionRepository extends JpaRepository<Inspeccion, Long> {
}
