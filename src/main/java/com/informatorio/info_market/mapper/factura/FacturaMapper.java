package com.informatorio.info_market.mapper.factura;

import java.math.BigDecimal;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.informatorio.info_market.domain.Factura;
import com.informatorio.info_market.dto.factura.FacturaDto;
import com.informatorio.info_market.mapper.carrito.CarritoMapper;

@Mapper(componentModel = "spring", uses = { CarritoMapper.class})
public interface FacturaMapper {
    
    @Mapping(target = "total", expression = "java(calcularTotalCarrito(factura))")
    FacturaDto facturaToFacturaDto(Factura factura);

    default BigDecimal calcularTotalCarrito(Factura factura){

        return factura.getCarrito().getItemsCarrito().stream() //hacemos un stream
                                                .map(item -> BigDecimal.valueOf( item.getProducto().getPrecio()) //tomamos los items del carrito y obtenemos los prod. con su precio
                                                                .multiply(BigDecimal.valueOf(item.getCantidad()))) //multiplicamos prod con cantidad
                                                .reduce(BigDecimal.ZERO, BigDecimal::add); //reducimos desde el primer valor y sumamos hasta el final. Devolvemos un Big decimal total
    }
    
}
