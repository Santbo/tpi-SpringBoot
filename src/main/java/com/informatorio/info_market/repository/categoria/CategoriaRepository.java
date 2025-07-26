package com.informatorio.info_market.repository.categoria;

import org.springframework.data.jpa.repository.JpaRepository;

import com.informatorio.info_market.domain.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
    
}
