package com.informatorio.info_market.service.factura;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.informatorio.info_market.domain.Carrito;
import com.informatorio.info_market.domain.Factura;
import com.informatorio.info_market.dto.factura.FacturaDto;

public interface FacturaService {
    

    Factura crearFactura(Carrito idCarrito);
    List<FacturaDto> getAllFacturas(LocalDate fechaDesde, LocalDate fechaHasta);

    FacturaDto getFacturaById(Long idFactura);

    List<FacturaDto> getAllFacturaByUserId(UUID idUser);
}
