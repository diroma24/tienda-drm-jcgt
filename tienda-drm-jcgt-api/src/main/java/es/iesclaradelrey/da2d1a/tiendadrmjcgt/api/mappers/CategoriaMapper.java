package es.iesclaradelrey.da2d1a.tiendadrmjcgt.api.mappers;

import es.iesclaradelrey.da2d1a.tiendadrmjcgt.api.dtos.CategoriaDto;
import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities.Categoria;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {

    CategoriaDto toDto(Categoria categoria);

    List<CategoriaDto> toDtoList(List<Categoria> categorias);
}