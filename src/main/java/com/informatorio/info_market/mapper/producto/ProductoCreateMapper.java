package com.informatorio.info_market.mapper.producto;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.informatorio.info_market.domain.Categoria;
import com.informatorio.info_market.domain.Producto;
import com.informatorio.info_market.dto.producto.ProductoCreateDto;
import com.informatorio.info_market.exceptions.notfound.NotFoundException;
import com.informatorio.info_market.repository.categoria.CategoriaRepository;



@Mapper(componentModel = "spring")
public abstract class ProductoCreateMapper {
    
    @Autowired
    protected CategoriaRepository categoriaRepository;

    @Mapping(target = "categorias", source = "categorias")
    public abstract Producto productoDtoCreateToProducto (ProductoCreateDto productoCreateDto);

    protected Categoria map(Long id){
        //Logica para buscar por id las categorias

        return categoriaRepository.findById(id)
                        .orElseThrow( () -> new NotFoundException("No se encontro la categoría con el id" + id));
    }

    protected List<Categoria> map(List<Long> ids) {
        return ids.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }
}
