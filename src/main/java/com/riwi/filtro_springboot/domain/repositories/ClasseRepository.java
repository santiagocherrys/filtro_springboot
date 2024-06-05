package com.riwi.filtro_springboot.domain.repositories;

import com.riwi.filtro_springboot.domain.entities.Classe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClasseRepository extends JpaRepository<Classe,Long> {
}
