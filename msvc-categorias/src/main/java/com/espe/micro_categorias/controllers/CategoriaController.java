package com.espe.micro_categorias.controllers;

import com.espe.micro_categorias.models.Categoria;
import com.espe.micro_categorias.models.CategoriaProducto;
import com.espe.micro_categorias.services.CategoriaService;
import com.espe.micro_categorias.Utils.Validation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/categoria")
@CrossOrigin(origins = "http://localhost:5173")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    // Crear una nueva categoría
    @PostMapping
    public ResponseEntity<?> createCategoria(@Valid @RequestBody Categoria categoria, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = Validation.getValidationErrors(result);
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        Categoria newCategoria = categoriaService.saveCategoria(categoria);
        return new ResponseEntity<>(newCategoria, HttpStatus.CREATED);
    }

    // Obtener una categoría por ID
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getCategoriaById(@PathVariable Long id) {
        Optional<Categoria> categoria = categoriaService.getCategoriaById(id);
        return categoria.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Obtener todas las categorías
    @GetMapping
    public ResponseEntity<List<Categoria>> getAllCategorias() {
        List<Categoria> categorias = categoriaService.getAllCategorias();
        return new ResponseEntity<>(categorias, HttpStatus.OK);
    }

    // Actualizar una categoría
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategoria(@PathVariable Long id, @Valid @RequestBody Categoria categoria, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = Validation.getValidationErrors(result);
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        try {
            Categoria updatedCategoria = categoriaService.updateCategoria(id, categoria);
            return new ResponseEntity<>(updatedCategoria, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar una categoría
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategoria(@PathVariable Long id) {
        try {
            categoriaService.deleteCategoria(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // Agregar un producto a una categoría
    @PostMapping("/{categoriaId}/productos/{productoId}")
    public ResponseEntity<?> addProductoToCategoria(
            @PathVariable Long categoriaId, @PathVariable Long productoId) {
        try {
            CategoriaProducto categoriaProducto = categoriaService.addProductoToCategoria(categoriaId, productoId);
            return new ResponseEntity<>(categoriaProducto, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // Obtener todos los productos en una categoría
    @GetMapping("/{categoriaId}/productos")
    public ResponseEntity<List<Long>> getProductosInCategoria(@PathVariable Long categoriaId) {
        List<Long> productoIds = categoriaService.getProductosInCategoria(categoriaId);
        return new ResponseEntity<>(productoIds, HttpStatus.OK);
    }

    // Eliminar un producto de una categoría
    @DeleteMapping("/{categoriaId}/productos/{productoId}")
    public ResponseEntity<?> removeProductoFromCategoria(
            @PathVariable Long categoriaId, @PathVariable Long productoId) {
        try {
            categoriaService.removeProductoFromCategoria(categoriaId, productoId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // Obtener todas las categorías asociadas a un producto
    @GetMapping("/producto/{productoId}/categorias")
    public ResponseEntity<List<Categoria>> getCategoriasForProducto(@PathVariable Long productoId) {
        List<Categoria> categorias = categoriaService.getCategoriasForProducto(productoId);
        if (categorias.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(categorias, HttpStatus.OK);
    }
}
