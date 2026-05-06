package es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarritoDto {
    // Lista de todos los productos añadidos[cite: 1]
    private List<LineaCarritoDto> lineas;

    // El número de productos distintos (con consulta JPQL)[cite: 1]
    private Long numeroProductosDistintos;

    // El número de unidades totales (con consulta JPQL)[cite: 1]
    private Long numeroUnidadesTotales;

    // El importe total del carro (con consulta JPQL)[cite: 1]
    private Double importeTotal;
}