package com.informatorio.info_market.service.producto;

import java.util.List;
import java.util.UUID;

import com.informatorio.info_market.domain.Producto;
import com.informatorio.info_market.dto.producto.ProductoCreateDto;
import com.informatorio.info_market.dto.producto.ProductoDto;

public interface ProductoService {
    
    List<ProductoDto> getAllProductos(int minStock, Double minPrice, Double maxPrice);
    ProductoDto getProductoById(UUID id);

    ProductoDto crearProducto(ProductoCreateDto producto);

    Producto getProductoEntityById(UUID id);

    ProductoDto updateProducto(ProductoCreateDto producto, UUID productoId);

    void deleteProducto(UUID id);

    void descontarStock(Producto producto, int cantidad);


}
