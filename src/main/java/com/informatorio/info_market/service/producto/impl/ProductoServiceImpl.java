package com.informatorio.info_market.service.producto.impl;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.informatorio.info_market.domain.Producto;
import com.informatorio.info_market.dto.producto.ProductoCreateDto;
import com.informatorio.info_market.dto.producto.ProductoDto;
import com.informatorio.info_market.exceptions.badrequest.StockInsuficienteException;
import com.informatorio.info_market.exceptions.notfound.NotFoundException;
import com.informatorio.info_market.mapper.producto.ProductoCreateMapper;
import com.informatorio.info_market.mapper.producto.ProductoMapper;
import com.informatorio.info_market.repository.producto.ProductoRepository;
import com.informatorio.info_market.service.producto.ProductoService;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class ProductoServiceImpl implements ProductoService{
    
    private ProductoRepository productoRepository;
    private ProductoMapper productoMapper;
    private ProductoCreateMapper productoCreateMapper;

    @Override
    public List<ProductoDto> getAllProductos(int minStock, Double minPrice, Double maxPrice){

        List<Producto> productos;

        if(minStock == 0 && minPrice == 0 && maxPrice == 0){

            productos =  productoRepository.findAll(); //Traemos todos los campos de la tabla Producto de la BD.
            
           
        } else if (minStock > 0 && maxPrice > 0){
            
            productos = productoRepository.findAllByStockIsGreaterThanAndPrecioIsBetween(minStock, minPrice, maxPrice);
        
        } else if(maxPrice > 0){

            productos = productoRepository.findAllByPrecioIsLessThan(maxPrice);
        } else{
            
            productos = productoRepository.findAllByStockIsGreaterThan(minStock);
        }

         return productos.stream()
                            .map( producto -> productoMapper.productoToProductoDto(producto))
                            .toList();

        
    }

    @Override
    public ProductoDto getProductoById(UUID id){

        Optional<Producto> producto = productoRepository.findById(id);

        if(producto.isPresent()){

            return productoMapper.productoToProductoDto(producto.get());
        } else{
            throw new NotFoundException("No se encontró el producto con id:" + id);
        }

    }

    @Override
    public ProductoDto crearProducto(ProductoCreateDto producto){

        Producto productoToCreate = productoCreateMapper.productoDtoCreateToProducto(producto);

        productoToCreate.setFechaDeCreacion(LocalDate.now());
        productoToCreate.setFechaActualizacion(LocalDate.now());

        return productoMapper.productoToProductoDto( productoRepository.save(productoToCreate) );
    }

    @Override
    public ProductoDto updateProducto(ProductoCreateDto producto, UUID productoId){


        Optional<Producto> productoToUpdate = productoRepository.findById(productoId);

        if(productoToUpdate.isPresent()){

            Producto productoUpdated = productoCreateMapper.productoDtoCreateToProducto(producto);

            productoUpdated.setId(productoId);
            productoUpdated.setFechaDeCreacion(productoToUpdate.get().getFechaDeCreacion());
            productoUpdated.setFechaActualizacion(LocalDate.now());

            productoRepository.save(productoUpdated);
            return productoMapper.productoToProductoDto(productoUpdated);
        }else{
            throw new NotFoundException("No se encontró el producto con id:" + productoId);
        }

        
    }

    @Override
    public void deleteProducto(UUID id){

        if (productoRepository.existsById(id)){

            productoRepository.deleteById(id);
        } else{
            throw new NotFoundException("No se encontró el producto con id:" + id);
        }


    }

    @Override
    public Producto getProductoEntityById(UUID id) {

        Optional<Producto> producto = productoRepository.findById(id);
        if (producto.isPresent()){
            return producto.get();
        }
        else {
            throw new NotFoundException("No se encontro el producto con id: " + id);
        }
    }

    @Override
    public void descontarStock(Producto producto, int cantidad){
        //este metodo controla la cantidad en stock y la cantidad que se quiere descontar

        if(producto.getStock() < cantidad){
            //ejecutar excepcion
            throw new StockInsuficienteException("No existe stock insuficiente del producto");
        } else {

            producto.setStock(producto.getStock()- cantidad);
            productoRepository.save(producto);
        }
    }
}
