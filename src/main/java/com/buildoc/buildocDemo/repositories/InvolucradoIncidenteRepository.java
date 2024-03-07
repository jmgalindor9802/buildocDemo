package com.buildoc.buildocDemo.repositories;

import com.buildoc.buildocDemo.entities.InvolucradoIncidente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvolucradoIncidenteRepository extends JpaRepository<InvolucradoIncidente, Long> {
}
