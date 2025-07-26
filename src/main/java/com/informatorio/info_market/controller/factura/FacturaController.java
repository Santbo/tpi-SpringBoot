package com.informatorio.info_market.controller.factura;


import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.informatorio.info_market.domain.Carrito;
import com.informatorio.info_market.domain.Factura;
import com.informatorio.info_market.dto.factura.FacturaDto;
import com.informatorio.info_market.service.factura.FacturaService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/v1/facturas")
@AllArgsConstructor
public class FacturaController {

    private FacturaService facturaService;
    
    public Factura generarFactura(Carrito idCarrito){

        return facturaService.crearFactura(idCarrito);
              
    } 

    @GetMapping()
    public List<FacturaDto> getAllFacturas( @RequestParam(value="fechaDesde", required=false) LocalDate fechaDesde,
                                            @RequestParam(value="fechaHasta", required=false) LocalDate fechaHasta
    ){

        return facturaService.getAllFacturas(fechaDesde, fechaHasta);
    }

    @GetMapping("/{idFactura}")
    public FacturaDto getFacturaById( @PathVariable Long idFactura){

        return facturaService.getFacturaById(idFactura);
    }

    @GetMapping("facturaUsuario/{idUser}")
    public List<FacturaDto> getAllFacturaByUserId (@PathVariable UUID idUser){

        return facturaService.getAllFacturaByUserId(idUser);
    }

}
