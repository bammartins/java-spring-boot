package com.bmartins.modelagem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bmartins.modelagem.domain.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Integer>{

}
