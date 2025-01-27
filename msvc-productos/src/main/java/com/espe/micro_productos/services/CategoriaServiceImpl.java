package com.espe.micro_productos.services;

import com.espe.micro_productos.models.Categoria;
import com.espe.micro_productos.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServiceImpl implements CategoriaService {
    @Autowired
    private CategoriaRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Categoria> listar() {
        return (List<Categoria>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Categoria> porId(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Categoria guardar(Categoria categoria) {
        return repository.save(categoria);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}

