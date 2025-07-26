package com.informatorio.info_market.mapper.carrito;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.informatorio.info_market.domain.Carrito;
import com.informatorio.info_market.dto.carrito.CarritoDto;
import com.informatorio.info_market.mapper.item.ItemCarritoMapper;

@Mapper(componentModel = "spring", uses = { ItemCarritoMapper.class})
public interface CarritoMapper {
    

    @Mapping(target = "estado", source = "estadoCarrito")
    @Mapping(target = "factura", source = "factura.id")
    
    CarritoDto carritoToCarritoDto(Carrito carrito);

    
}
