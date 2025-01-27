package com.espe.micro_categorias.repositories;

import com.espe.micro_categorias.models.CategoriaProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriaProductoRepository extends JpaRepository<CategoriaProducto, Long> {
    // Encuentra todos los registros por ID de categoría
    List<CategoriaProducto> findByCategoriaId(Long categoriaId);

    // Encuentra todos los registros por ID de producto
    List<CategoriaProducto> findByProductoId(Long productoId);

    // Encuentra un registro específico por ID de categoría e ID de producto
    Optional<CategoriaProducto> findByCategoriaIdAndProductoId(Long categoriaId, Long productoId);
}
