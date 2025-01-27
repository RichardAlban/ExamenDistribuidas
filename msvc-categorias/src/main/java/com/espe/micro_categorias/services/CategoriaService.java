package com.espe.micro_categorias.services;

import com.espe.micro_categorias.Client.ProductoFeignClient;
import com.espe.micro_categorias.models.Categoria;
import com.espe.micro_categorias.models.CategoriaProducto;
import com.espe.micro_categorias.repositories.CategoriaProductoRepository;
import com.espe.micro_categorias.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private CategoriaProductoRepository categoriaProductoRepository;

    // Crear o actualizar una categoría
    public Categoria saveCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    // Obtener una categoría por su ID
    public Optional<Categoria> getCategoriaById(Long id) {
        return categoriaRepository.findById(id);
    }

    // Obtener todas las categorías
    public List<Categoria> getAllCategorias() {
        return categoriaRepository.findAll();
    }

    // Actualizar una categoría
    public Categoria updateCategoria(Long id, Categoria updatedCategoria) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        categoria.setNombre(updatedCategoria.getNombre());
        return categoriaRepository.save(categoria);
    }

    // Eliminar una categoría por ID
    public void deleteCategoria(Long id) {
        if (!categoriaRepository.existsById(id)) {
            throw new RuntimeException("Categoría no encontrada");
        }
        categoriaRepository.deleteById(id);
    }

    // Agregar un producto a una categoría
    public CategoriaProducto addProductoToCategoria(Long categoriaId, Long productoId) {
        Categoria categoria = categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        // Verificar si el producto ya está en la categoría
        Optional<CategoriaProducto> existingProducto = categoriaProductoRepository.findByCategoriaIdAndProductoId(categoriaId, productoId);
        if (existingProducto.isPresent()) {
            throw new RuntimeException("El producto ya está en la categoría");
        }

        // Crear y guardar la relación entre la categoría y el producto
        CategoriaProducto categoriaProducto = new CategoriaProducto();
        categoriaProducto.setCategoria(categoria);
        categoriaProducto.setProductoId(productoId);
        return categoriaProductoRepository.save(categoriaProducto);
    }

    // Obtener todos los productos en una categoría
    public List<Long> getProductosInCategoria(Long categoriaId) {
        List<CategoriaProducto> categoriaProductos = categoriaProductoRepository.findByCategoriaId(categoriaId);

        List<Long> productoIds = new ArrayList<>();
        for (CategoriaProducto categoriaProducto : categoriaProductos) {
            productoIds.add(categoriaProducto.getProductoId());
        }
        return productoIds;
    }

    // Eliminar un producto de una categoría
    public void removeProductoFromCategoria(Long categoriaId, Long productoId) {
        CategoriaProducto categoriaProducto = categoriaProductoRepository.findByCategoriaIdAndProductoId(categoriaId, productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado en la categoría"));

        categoriaProductoRepository.delete(categoriaProducto);
    }

    // Obtener categorías asociadas a un producto
    public List<Categoria> getCategoriasForProducto(Long productoId) {
        List<CategoriaProducto> categoriaProductos = categoriaProductoRepository.findByProductoId(productoId);
        List<Categoria> categorias = new ArrayList<>();

        for (CategoriaProducto categoriaProducto : categoriaProductos) {
            categorias.add(categoriaProducto.getCategoria());
        }

        return categorias;
    }
}
