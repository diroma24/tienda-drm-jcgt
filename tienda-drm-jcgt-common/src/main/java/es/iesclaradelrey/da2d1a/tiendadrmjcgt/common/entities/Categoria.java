package es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa una categoría de productos dentro de la tienda.
 * <p>
 * Esta clase utiliza anotaciones de Lombok para generar automáticamente
 * los métodos getter, setter, equals, hashCode, toString y constructores.
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Categoria {

    /**
     * Identificador único de la categoría.
     * Vinculado a la llave primaria en la base de datos.
     */
    private Long id;

    /**
     * Nombre descriptivo de la categoría (ej. "Sobres", "Cartas").
     */
    private String nombre;

    /**
     * Descripción sobre qué tipo de elementos contiene esta categoría.
     */
    private String descripcion;

    /**
     * Nombre del archivo de imagen de la categoría.
     */
    private String imagen;
}