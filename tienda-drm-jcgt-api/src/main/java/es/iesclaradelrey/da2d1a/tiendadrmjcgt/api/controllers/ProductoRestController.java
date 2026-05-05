package es.iesclaradelrey.da2d1a.tiendadrmjcgt.api.controllers;

import es.iesclaradelrey.da2d1a.tiendadrmjcgt.api.dtos.ProductoDto;
import es.iesclaradelrey.da2d1a.tiendadrmjcgt.api.services.ProductoApiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products") // URL requerida[cite: 1]
public class ProductoRestController {
    private final ProductoApiService productoApiService;

    public ProductoRestController(ProductoApiService productoApiService) {
        this.productoApiService = productoApiService;
    }

    @GetMapping
    public List<ProductoDto> getAll() {
        return productoApiService.getAllSortedByName();
    }
}