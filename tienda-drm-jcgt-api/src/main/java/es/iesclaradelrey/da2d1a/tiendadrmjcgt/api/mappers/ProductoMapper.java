package es.iesclaradelrey.da2d1a.tiendadrmjcgt.api.mappers;

import es.iesclaradelrey.da2d1a.tiendadrmjcgt.api.dtos.ProductoDto;
import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities.Producto;
import org.mapstruct.Mapper;
import java.util.List;

// Usamos CategoriaMapper para que sepa mapear la lista de categorías interna
@Mapper(componentModel = "spring", uses = {CategoriaMapper.class})
public interface ProductoMapper {
    ProductoDto toDto(Producto producto);
    List<ProductoDto> toDtoList(List<Producto> productos);
}