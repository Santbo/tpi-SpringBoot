package com.informatorio.info_market.dto.producto;

import java.util.List;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
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
public class ProductoCreateDto {
    
    @NotBlank(message = "El nombre del producto no puede esta vacío") //No puede ser null si tener espacio en blanco
    private String nombre;

    @NotBlank(message = "El nombre de la descripcion no puede esta vacío")
    @Size(max = 50, message = "La descripcion debe tener como máximo 50 caracteres") //Tamaño maximo y msj de error
    private String descripcion;
    
    @Min(value = 0, message = "Se debe tener un precio minimo de 0") //Valor minimo con msj si no se cumple
    private Double precio;
    
    @Min(value = 0, message = "Se debe tener un stock minimo de 0")
    private int stock;

    @NotEmpty(message = "se debe tener como mínimo una categoria para el producto") //No recibe collections, map vacios
    private List<Long> categorias;
}
