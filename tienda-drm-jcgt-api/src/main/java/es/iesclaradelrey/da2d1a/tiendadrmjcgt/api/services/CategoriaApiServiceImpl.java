package es.iesclaradelrey.da2d1a.tiendadrmjcgt.api.services;

import es.iesclaradelrey.da2d1a.tiendadrmjcgt.api.dtos.CategoriaDto;
import es.iesclaradelrey.da2d1a.tiendadrmjcgt.api.mappers.CategoriaMapper;
import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.repositories.CategoriaRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoriaApiServiceImpl implements CategoriaApiService {

    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;

    public CategoriaApiServiceImpl(CategoriaRepository categoriaRepository, CategoriaMapper categoriaMapper) {
        this.categoriaRepository = categoriaRepository;
        this.categoriaMapper = categoriaMapper;
    }

    @Override
    public List<CategoriaDto> getAllSortedByName() {
        // Usamos Sort de PagingAndSortingRepository (requisito 3.5)
        return categoriaMapper.toDtoList(categoriaRepository.findAll(Sort.by("nombre")));
    }
}