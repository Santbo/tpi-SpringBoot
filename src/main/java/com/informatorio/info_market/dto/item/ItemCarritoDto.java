package com.informatorio.info_market.dto.item;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemCarritoDto {
    
    private UUID id;
    private String nombreProducto;
    private int cantidad;
    private double precio;
}
