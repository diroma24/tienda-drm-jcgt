package es.iesclaradelrey.da2d1a.tiendadrmjcgt.api.services;

import es.iesclaradelrey.da2d1a.tiendadrmjcgt.api.dtos.ProductoDto;

import java.util.List;

public interface ProductoApiService {
    List<ProductoDto> getAllSortedByName();
}