package com.informatorio.info_market.dto.producto;

import java.util.List;
import java.util.UUID;

import com.informatorio.info_market.dto.categoria.CategoriaDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProductoDto {
    
    private UUID id;
    private String nombre;
    private String descripcion;
    private double precio;
    private int stock;
    private List<CategoriaDto> categorias;

}
