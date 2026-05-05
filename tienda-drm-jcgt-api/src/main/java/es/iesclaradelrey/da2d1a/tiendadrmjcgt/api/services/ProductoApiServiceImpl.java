package es.iesclaradelrey.da2d1a.tiendadrmjcgt.api.services;

import es.iesclaradelrey.da2d1a.tiendadrmjcgt.api.dtos.ProductoDto;
import es.iesclaradelrey.da2d1a.tiendadrmjcgt.api.mappers.ProductoMapper;
import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities.Producto;
import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.repositories.ProductoRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoApiServiceImpl implements ProductoApiService {
    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;

    public ProductoApiServiceImpl(ProductoRepository productoRepository, ProductoMapper productoMapper) {
        this.productoRepository = productoRepository;
        this.productoMapper = productoMapper;
    }

    @Override
    public List<ProductoDto> getAllSortedByName() {
        // findAll(Sort) es un método de PagingAndSortingRepository
        return productoMapper.toDtoList(productoRepository.findAll(Sort.by("nombre")));
    }

    @Override
    public List<ProductoDto> getProductsByCategory(Long categoryId) {
        // Usamos el método de consulta derivada pasando el objeto Sort por separado
        Sort sort = Sort.by("nombre");
        List<Producto> productos = productoRepository.findByCategoriasId(categoryId, sort);
        return productoMapper.toDtoList(productos);
    }
}
