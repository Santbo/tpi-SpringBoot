package com.informatorio.info_market.service.carrito.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.informatorio.info_market.controller.factura.FacturaController;
import com.informatorio.info_market.domain.Carrito;
import com.informatorio.info_market.domain.ItemCarrito;
import com.informatorio.info_market.domain.Producto;
import com.informatorio.info_market.domain.Usuario;
import com.informatorio.info_market.dto.carrito.CarritoDto;
import com.informatorio.info_market.enumerations.EstadoCarritoEnum;
import com.informatorio.info_market.exceptions.notfound.NotFoundException;
import com.informatorio.info_market.mapper.carrito.CarritoMapper;
import com.informatorio.info_market.repository.carrito.CarritoRepository;
import com.informatorio.info_market.service.carrito.CarritoService;
import com.informatorio.info_market.service.item.ItemService;
import com.informatorio.info_market.service.producto.ProductoService;
import com.informatorio.info_market.service.usuario.UsuarioService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CarritoServiceImpl implements CarritoService {
    
    private CarritoRepository carritoRepository;
    private ProductoService productoService;
    private UsuarioService usuarioService;
    private ItemService itemService;
    private CarritoMapper carritoMapper;
    private FacturaController facturaController;

    @Override
    public void agregarProducto (UUID idUser, UUID idProducto){

        //Buscamos producto

        Producto producto = productoService.getProductoEntityById(idProducto); //necesitamos que el service devuelva un producto ademas del DTO

        //Buscamos usuario
        Usuario usuario = usuarioService.getUsuarioEntityById(idUser);

        //Obtenemos el carrito que este abierto, sino creamos uno
        Optional<Carrito> carrito = getCarritoConEstado(EstadoCarritoEnum.ABIERTO, usuario.getCarritos());

        if (carrito.isPresent()){
            //existe un carrito
            //Agregar el producto al carrito
            ItemCarrito itemCarrito = itemService.crearItemCarrito(carrito.get(), producto, 1);

            carrito.get().getItemsCarrito().add(itemCarrito);
            //Actualizar el carrito
            carritoRepository.save(carrito.get());
        } else{
            //creamos un nuevo carrito
            Carrito carritoNuevo = new Carrito();
            carritoNuevo.setEstadoCarrito(EstadoCarritoEnum.ABIERTO);
            carritoNuevo.setUsuario(usuario);
            carritoNuevo.setFactura(null);
            carritoNuevo.setFechaActualizacion(LocalDate.now());
            carritoNuevo.setFechaCreacion(LocalDate.now());
            //crear un item nuevo con un producto
            ItemCarrito itemCarrito = itemService.crearItemCarrito(carritoNuevo, producto, 1);


            //Agregar el item al nuevo carrito
            carritoNuevo.getItemsCarrito().add(itemCarrito);
            //Guardar
            carritoRepository.save(carritoNuevo);

           

        }
    }

    @Override
    public Optional<Carrito> getCarritoConEstado(EstadoCarritoEnum estadoCarritoEnum, List<Carrito> carritos){
        return carritos.stream().filter(carrito -> estadoCarritoEnum.equals( carrito.getEstadoCarrito() )).findFirst(); //filtra los carritos con el estado que le indicamos o nada, y el find first devuelve el primer carrito abierto si no un Optional empty.
    }

    @Override
    public List<CarritoDto> getAllCarritos(EstadoCarritoEnum estadoCarrito){

        List<Carrito> carritos = (estadoCarrito != null) ? carritoRepository.findByEstadoCarrito(estadoCarrito) : carritoRepository.findAll();
        
        return carritos.stream()
                       .map(carritoMapper::carritoToCarritoDto)
                       .toList();
        

    }

    @Override
    public List<CarritoDto> getCarritoByIdUser(UUID usuarioId){

        List<Carrito> carritos = carritoRepository.findByUsuarioId(usuarioId);

        return carritos.stream()
                       .map(carritoMapper::carritoToCarritoDto)
                       .toList();
    }

    @Override
    public CarritoDto cerrarCarrito(UUID usuarioId){

        Optional<Carrito> carritoAbierto = carritoRepository.findByUsuarioIdAndEstadoCarrito(usuarioId, EstadoCarritoEnum.ABIERTO);

        if (carritoAbierto.isPresent()){

            Carrito carritoCerrado = carritoAbierto.get();

            carritoCerrado.setEstadoCarrito(EstadoCarritoEnum.CERRADO);
            carritoCerrado.setFactura(facturaController.generarFactura(carritoCerrado));

            return carritoMapper.carritoToCarritoDto(carritoRepository.save(carritoCerrado));

        } else {

            throw new NotFoundException("No se encontró un usuario con el id: " + usuarioId + " o el Usuario no posee un carrito abierto");
        }


        
    }


}
