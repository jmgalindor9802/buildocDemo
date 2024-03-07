package com.buildoc.buildocDemo.repositories;

import com.buildoc.buildocDemo.entities.TipoInspeccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoInspeccionRepository extends JpaRepository<TipoInspeccion, Long> {
}
