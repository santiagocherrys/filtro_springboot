package com.riwi.filtro_springboot.domain.repositories;

import com.riwi.filtro_springboot.domain.entities.Multimedia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MultimediaRepository extends JpaRepository<Multimedia, Long> {
}
