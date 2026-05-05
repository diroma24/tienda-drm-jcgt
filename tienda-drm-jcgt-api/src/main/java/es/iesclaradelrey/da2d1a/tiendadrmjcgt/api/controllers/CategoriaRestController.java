package es.iesclaradelrey.da2d1a.tiendadrmjcgt.api.controllers;

import es.iesclaradelrey.da2d1a.tiendadrmjcgt.api.dtos.CategoriaDto;
import es.iesclaradelrey.da2d1a.tiendadrmjcgt.api.dtos.ProductoDto;
import es.iesclaradelrey.da2d1a.tiendadrmjcgt.api.services.CategoriaApiService;
import es.iesclaradelrey.da2d1a.tiendadrmjcgt.api.services.ProductoApiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoriaRestController {

    private final CategoriaApiService categoriaApiService;
    private final ProductoApiService productoApiService;

    // EL CAMBIO ESTÁ AQUÍ: Añadir ProductoApiService al constructor
    public CategoriaRestController(CategoriaApiService categoriaApiService, ProductoApiService productoApiService) {
        this.categoriaApiService = categoriaApiService;
        this.productoApiService = productoApiService; // <-- Inicialización de la variable
    }

    @GetMapping
    public List<CategoriaDto> getAll() {
        return categoriaApiService.getAllSortedByName();
    }

    @GetMapping("/{categoryId}/products")
    public List<ProductoDto> getProductsByCategory(@PathVariable Long categoryId) {
        return productoApiService.getProductsByCategory(categoryId);
    }
}