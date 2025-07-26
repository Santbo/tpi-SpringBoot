package com.informatorio.info_market.dto.carrito;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.informatorio.info_market.dto.item.ItemCarritoDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarritoDto {
    
    private UUID id;
    private String estado;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String factura;
    private List<ItemCarritoDto> itemsCarrito;
}
