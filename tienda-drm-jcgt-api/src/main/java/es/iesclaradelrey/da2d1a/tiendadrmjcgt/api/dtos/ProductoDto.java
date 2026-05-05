package es.iesclaradelrey.da2d1a.tiendadrmjcgt.api.dtos;

import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
public class ProductoDto {
    private Long id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private MarcaDto marca; // Incluye datos de la marca
    private Set<CategoriaDto> categorias; // Incluye datos de las categorías
}