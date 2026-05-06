package es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.dtos;

import lombok.Data;

@Data
public class AñadirProductoRequestDto {
    private Long productoId;
    private Integer unidades;
}