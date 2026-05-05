package es.iesclaradelrey.da2d1a.tiendadrmjcgt.api.controllers;

import es.iesclaradelrey.da2d1a.tiendadrmjcgt.api.dtos.CategoriaDto;
import es.iesclaradelrey.da2d1a.tiendadrmjcgt.api.services.CategoriaApiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/v1/categories") // URL requerida
public class CategoriaRestController {

    private final CategoriaApiService categoriaApiService;

    public CategoriaRestController(CategoriaApiService categoriaApiService) {
        this.categoriaApiService = categoriaApiService;
    }

    @GetMapping
    public List<CategoriaDto> getAll() {
        return categoriaApiService.getAllSortedByName();
    }
}