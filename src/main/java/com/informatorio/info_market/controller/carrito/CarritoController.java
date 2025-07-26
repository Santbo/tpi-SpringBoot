package com.informatorio.info_market.controller.carrito;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.informatorio.info_market.dto.carrito.CarritoDto;
import com.informatorio.info_market.enumerations.EstadoCarritoEnum;
import com.informatorio.info_market.service.carrito.CarritoService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/v1/carritos")
@AllArgsConstructor
public class CarritoController {
    
    private CarritoService carritoService;

    @PostMapping("agregarProducto/{idUser}/{idProducto}")
    public ResponseEntity<Object> agregarProducto(
        @PathVariable UUID idUser,
        @PathVariable UUID idProducto) {

            carritoService.agregarProducto(idUser, idProducto);
            return ResponseEntity.ok().build();

        }

    @GetMapping() //Anotacion a nivel de metodo
    public List<CarritoDto> getAllCarritos(
                    @RequestParam(value = "estadoCarrito", required = false)EstadoCarritoEnum estadoCarrito
    ) {

        
        return carritoService.getAllCarritos( estadoCarrito );
    }

    //Obtener el carrito de un usuario particular
    @GetMapping("/{idUser}")
    public List<CarritoDto> getCarritoByIdUser(
        @PathVariable UUID idUser
    ) {
        return carritoService.getCarritoByIdUser( idUser);

    }

    @PutMapping("{idUser}")
    public ResponseEntity<CarritoDto> cerrarCarrito(
                    @PathVariable UUID idUser
    ){
        CarritoDto carritoDto = carritoService.cerrarCarrito(idUser);

        return ResponseEntity.created(URI.create("api/v1/factura/" + carritoDto.getFactura()))
                             .contentType(MediaType.APPLICATION_JSON)
                             .body(carritoDto);
    }

}
