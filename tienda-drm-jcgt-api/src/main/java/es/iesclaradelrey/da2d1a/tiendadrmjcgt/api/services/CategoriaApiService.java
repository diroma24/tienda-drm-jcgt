package es.iesclaradelrey.da2d1a.tiendadrmjcgt.api.services;

import es.iesclaradelrey.da2d1a.tiendadrmjcgt.api.dtos.CategoriaDto;
import java.util.List;

public interface CategoriaApiService {
    List<CategoriaDto> getAllSortedByName();
}