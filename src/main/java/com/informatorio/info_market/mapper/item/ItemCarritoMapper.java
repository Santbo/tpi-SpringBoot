package com.informatorio.info_market.mapper.item;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.informatorio.info_market.domain.ItemCarrito;
import com.informatorio.info_market.dto.item.ItemCarritoDto;

@Mapper(componentModel = "spring")
public interface ItemCarritoMapper {

    @Mapping(source = "producto.nombre", target = "nombreProducto")
    @Mapping(target = "precio", source = "producto.precio")
    ItemCarritoDto itemCarritoToItemCarritoDto (ItemCarrito itemCarrito);

    
} 
    



