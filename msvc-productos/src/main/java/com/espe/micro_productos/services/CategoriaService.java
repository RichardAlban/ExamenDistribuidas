package com.espe.micro_productos.services;

import com.espe.micro_productos.models.Categoria;
import java.util.List;
import java.util.Optional;

public interface CategoriaService {
    List<Categoria> listar();
    Optional<Categoria> porId(Long id);
    Categoria guardar(Categoria categoria);
    void eliminar(Long id);
}
