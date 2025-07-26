package com.informatorio.info_market.controller.producto;


import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.informatorio.info_market.dto.producto.ProductoCreateDto;
import com.informatorio.info_market.dto.producto.ProductoDto;
import com.informatorio.info_market.service.producto.ProductoService;

import jakarta.validation.Valid;

@RestController // Anotacion a nivel de clase
@RequestMapping("api/v1/productos") //Anotacion a nivel de clase
public class ProductoController {
    
    @Autowired //anotacion a nivel de atributo, en este caso es mejor utilizarlo en un constructor, pero nuevas versiones no hay que colocarlo
    ProductoService productoService;

    public ProductoController(ProductoService productoService){

        this.productoService = productoService;
    }
    @GetMapping() //Anotacion a nivel de metodo
    public List<ProductoDto> getAllProductos(
                    @RequestParam(value = "minStock", defaultValue = "0", required = false)int minStock,
                    @RequestParam(value = "minPrice", defaultValue = "0", required = false)Double minPrice,
                    @RequestParam(value = "maxPrice", defaultValue = "0", required =  false) Double maxPrice
    ) {

        
        return productoService.getAllProductos( minStock, minPrice, maxPrice);
    }

    @PutMapping("/{productoId}")
    public ResponseEntity<ProductoDto> updateProducto(@Valid @RequestBody ProductoCreateDto producto, @PathVariable UUID productoId){

        ProductoDto productoDto = productoService.updateProducto(producto, productoId);
        //En el ResponseEntity de un Put no se pone un body ya que el noContent no devuelve contenido, se agrega un build()
        // lo que se puede hacer es agregar un ok() que te deja devolver un body
        //Diferencia entre body y build es que:
        //body: devuelve un cuerpo completo de la entidad
        //build: devuelve solamente el estado (codigo) que fue exitoso pero sin contenido
        return ResponseEntity
                .ok()
                .location(URI.create("api/v1/productos/" + productoId))
                .body(productoDto);
    }

    @PostMapping()
    public ResponseEntity<ProductoDto> crearProducto(@Valid @RequestBody ProductoCreateDto producto){

        ProductoDto productoDto =  productoService.crearProducto(producto);

        return ResponseEntity
                .created(URI.create("api/v1/productos/" + productoDto.getId() ) ) //Nos permite crear una URI a través de texto. Además el created nos permite ver donde podes encontrar este producto y que fue creado.
                .contentType(MediaType.APPLICATION_JSON) //indica que lo que estamos enviando es un JSON
                .body(productoDto);
    }           

    @GetMapping("/{productoId}")
    public ProductoDto getProductoById(@PathVariable UUID productoId){

        return productoService.getProductoById(productoId);
    }

    @DeleteMapping("/{productoId}")
    public void deleteProductoById(@PathVariable UUID productoId){

        productoService.deleteProducto(productoId);
        ResponseEntity.noContent().build();
    }

    
}
