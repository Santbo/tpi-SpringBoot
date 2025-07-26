package com.informatorio.info_market.dto.factura;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.informatorio.info_market.dto.carrito.CarritoDto;

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
public class FacturaDto {
    
    private Long id;
    private LocalDate fechaDeEmision;
    private CarritoDto carrito;
    private BigDecimal total;
}
