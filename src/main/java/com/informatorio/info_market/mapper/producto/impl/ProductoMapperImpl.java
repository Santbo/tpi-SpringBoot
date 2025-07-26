package com.informatorio.info_market.mapper.producto.impl;

import org.springframework.stereotype.Component;

import com.informatorio.info_market.domain.Producto;
import com.informatorio.info_market.dto.producto.ProductoDto;
import com.informatorio.info_market.mapper.categoria.CategoriaMapper;
import com.informatorio.info_market.mapper.producto.ProductoMapper;

import lombok.AllArgsConstructor;


@Component
@AllArgsConstructor
public class ProductoMapperImpl implements ProductoMapper{

    private CategoriaMapper categoriaMapper;


    //Aca iria un @autowired en el constructor pero ya no es necesario, y el constructor tampoco por lombok
    
    
    @Override
    public ProductoDto productoToProductoDto(Producto producto){

        ProductoDto productoDto = new ProductoDto();

        productoDto.setId(producto.getId());
        productoDto.setNombre(producto.getNombre());
        productoDto.setDescripcion(producto.getDescripcion());
        productoDto.setPrecio(producto.getPrecio());
        productoDto.setStock(producto.getStock());

        productoDto.setCategorias(producto.getCategorias()
                                  .stream()
                                  .map(categoria -> categoriaMapper.categoriaToCategoriaDto(categoria))
                                  .toList());

        

        

        return productoDto;
    }
}
