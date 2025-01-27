package com.espe.micro_productos.services;

import com.espe.micro_productos.models.Producto;
import com.espe.micro_productos.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    // Crear o actualizar un producto
    public Producto saveProducto(Producto producto) {
        // Validar que no exista un producto con el mismo nombre
        Optional<Producto> existingProducto = productoRepository.findByNombre(producto.getNombre());
        if (existingProducto.isPresent()) {
            throw new RuntimeException("Ya existe un producto con el nombre " + producto.getNombre() + ".");
        }
        return productoRepository.save(producto);
    }

    // Obtener un producto por ID
    public Optional<Producto> getProductoById(Long id) {
        return productoRepository.findById(id);
    }

    // Obtener todos los productos
    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    // Actualizar un producto por ID
    public Producto updateProducto(Long id, Producto updatedProducto) {
        return productoRepository.findById(id)
                .map(existingProducto -> {
                    existingProducto.setNombre(updatedProducto.getNombre());
                    existingProducto.setDescripcion(updatedProducto.getDescripcion());
                    existingProducto.setPrecio(updatedProducto.getPrecio());
                    return productoRepository.save(existingProducto);
                })
                .orElseThrow(() -> new RuntimeException("Producto con ID " + id + " no encontrado"));
    }

    // Eliminar un producto por ID
    public void deleteProducto(Long id) {
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Producto con ID " + id + " no encontrado");
        }
    }
}
