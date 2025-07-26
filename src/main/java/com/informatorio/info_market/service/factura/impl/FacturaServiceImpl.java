package com.informatorio.info_market.service.factura.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.informatorio.info_market.domain.Carrito;
import com.informatorio.info_market.domain.Factura;
import com.informatorio.info_market.dto.factura.FacturaDto;
import com.informatorio.info_market.enumerations.EstadoCarritoEnum;
import com.informatorio.info_market.exceptions.notfound.NotFoundException;
import com.informatorio.info_market.mapper.factura.FacturaMapper;
import com.informatorio.info_market.repository.carrito.CarritoRepository;
import com.informatorio.info_market.repository.factura.FacturaRepository;
import com.informatorio.info_market.service.factura.FacturaService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FacturaServiceImpl implements FacturaService{

    private FacturaRepository facturaRepository;
    private FacturaMapper facturaMapper;
    private CarritoRepository carritoRepository;

    @Override
    public Factura crearFactura(Carrito idCarrito){

        Factura creacionFactura = new Factura();

        creacionFactura.setCarrito(idCarrito);
        creacionFactura.setFechaDeEmision(LocalDate.now());

        return facturaRepository.save(creacionFactura);
        
    }

    @Override
    public List<FacturaDto> getAllFacturas(LocalDate fechaDesde, LocalDate fechaHasta){

        if (fechaDesde == null && fechaHasta instanceof LocalDate){

            return facturaRepository.findByFechaDeEmisionLessThanEqual(fechaHasta).stream()
                                                                         .map(facturaMapper::facturaToFacturaDto)
                                                                         .toList();
                                                                         
        } else if( fechaDesde instanceof LocalDate && fechaHasta == null){

            return facturaRepository.findByFechaDeEmisionGreaterThanEqual(fechaDesde).stream()
                                                                             .map(facturaMapper::facturaToFacturaDto)
                                                                             .toList();

        } else if ( fechaDesde instanceof LocalDate && fechaHasta instanceof LocalDate){

            return facturaRepository.findByFechaDeEmisionBetween(fechaDesde, fechaHasta).stream()
                                                                                        .map(facturaMapper::facturaToFacturaDto)
                                                                                        .toList();

        } else {

            return facturaRepository.findAll().stream().map(factura -> facturaMapper.facturaToFacturaDto(factura)).toList();
        }
    }

    @Override
    public FacturaDto getFacturaById(Long idFactura){

        Optional<Factura> factura = facturaRepository.findById(idFactura);

        if (factura.isPresent()){

            return facturaMapper.facturaToFacturaDto(factura.get());
        } else {

            throw new NotFoundException("No se encontró la factura con el id: " + idFactura);
        }
    }

    @Override
    public List<FacturaDto> getAllFacturaByUserId(UUID idUser){

        //buscar el carrito con el usuario (con el carrito DTO alcanza, ya que tengo lo necesario ahi)
        List<Carrito> carritosCerrados = carritoRepository.findAllByUsuarioIdAndEstadoCarrito(idUser, EstadoCarritoEnum.CERRADO);

        if (carritosCerrados.isEmpty() || carritosCerrados == null){

            throw new NotFoundException("No se encontro el usuario  con el id: " + idUser + " o no posee carritos cerrados");
        }
        
        //Busco las facturas del carrito y envio
        return carritosCerrados.stream().map(Carrito::getFactura).map(facturaMapper::facturaToFacturaDto).toList();

    }
    
}
