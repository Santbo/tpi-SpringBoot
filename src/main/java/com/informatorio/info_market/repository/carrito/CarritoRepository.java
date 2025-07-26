package com.informatorio.info_market.repository.carrito;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.informatorio.info_market.domain.Carrito;
import com.informatorio.info_market.enumerations.EstadoCarritoEnum;

public interface CarritoRepository extends JpaRepository<Carrito, UUID>{

    List<Carrito> findByEstadoCarrito(EstadoCarritoEnum estadoCarrito);

    List<Carrito> findByUsuarioId(UUID usuarioId);

    Optional<Carrito> findByUsuarioIdAndEstadoCarrito(UUID usuarioId, EstadoCarritoEnum estadoCarrito);

    List<Carrito>  findAllByUsuarioIdAndEstadoCarrito(UUID usuarioId, EstadoCarritoEnum estadoCarritoEnum);

}