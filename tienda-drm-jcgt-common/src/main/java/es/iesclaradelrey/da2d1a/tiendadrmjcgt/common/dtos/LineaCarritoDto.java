package es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LineaCarritoDto {
    private String nombreProducto;
    private Double precioUnitario;
    private Integer descuento;
    private Double precioConDescuento;
    private Integer unidades;
    private Double precioTotalLinea; // Se calcula como (unidades * precioConDescuento)
}