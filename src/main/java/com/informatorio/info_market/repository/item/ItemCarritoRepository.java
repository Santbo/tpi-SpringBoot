package com.informatorio.info_market.repository.item;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.informatorio.info_market.domain.ItemCarrito;

public interface ItemCarritoRepository extends JpaRepository<ItemCarrito, UUID>{
    
}
