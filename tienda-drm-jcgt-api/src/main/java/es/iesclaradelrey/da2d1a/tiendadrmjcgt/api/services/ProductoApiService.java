package es.iesclaradelrey.da2d1a.tiendadrmjcgt.api.services;

import es.iesclaradelrey.da2d1a.tiendadrmjcgt.api.dtos.ProductoDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductoApiService {
    List<ProductoDto> getAllSortedByName();

    // Añadir este método a la interfaz existente
    List<ProductoDto> getProductsByCategory(Long categoryId);
}