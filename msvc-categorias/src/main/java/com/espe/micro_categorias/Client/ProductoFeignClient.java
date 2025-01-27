package com.espe.micro_categorias.Client;

import com.espe.micro_categorias.models.Producto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "producto", url = "http://localhost:8001/producto")
public interface ProductoFeignClient {
    @GetMapping("/{id}")
    ResponseEntity<Producto> getPlayer(@PathVariable Long id);

    @GetMapping
    List<Producto> getAllPlayers();
}
