package com.espe.micro_productos.repositories;

import com.espe.micro_productos.models.Categoria;
import org.springframework.data.repository.CrudRepository;

public interface CategoriaRepository extends CrudRepository<Categoria, Long> {
}