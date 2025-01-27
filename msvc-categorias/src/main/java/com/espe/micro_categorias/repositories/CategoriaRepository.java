package com.espe.micro_categorias.repositories;

import com.espe.micro_categorias.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    // Encuentra una categoría por su nombre
    Optional<Categoria> findByNombre(String nombre);
}
