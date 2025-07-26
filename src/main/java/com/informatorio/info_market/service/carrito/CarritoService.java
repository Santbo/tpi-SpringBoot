package com.informatorio.info_market.service.carrito;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.transaction.annotation.Transactional;

import com.informatorio.info_market.domain.Carrito;
import com.informatorio.info_market.dto.carrito.CarritoDto;
import com.informatorio.info_market.enumerations.EstadoCarritoEnum;

public interface CarritoService {

    @Transactional
    void agregarProducto(UUID idUser,UUID idProducto);

    List<CarritoDto> getAllCarritos(EstadoCarritoEnum estadoCarrito);

    Optional<Carrito> getCarritoConEstado(EstadoCarritoEnum estadoCarritoEnum, List<Carrito> carritos);

    List<CarritoDto> getCarritoByIdUser(UUID usuarioId);

    @Transactional
    CarritoDto cerrarCarrito(UUID usuarioId);
}
