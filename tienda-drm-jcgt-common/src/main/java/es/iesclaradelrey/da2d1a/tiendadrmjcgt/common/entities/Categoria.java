package es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Representa una categoría de productos dentro de la tienda.
 * <p>
 * Esta clase utiliza anotaciones de Lombok para generar automáticamente
 * los métodos getter, setter, equals, hashCode, toString y constructores.
 * </p>
 */

@Entity // Indica que esta clase se mapea a una tabla de base de datos
@Table(name = "categorias") // Nombre de la tabla en H2
@Data //Genera automáticamente setters y getters, toString, equals y RequiredArgsConstructor
@NoArgsConstructor //Es necesario para que Hibernate funcione correctamente
@AllArgsConstructor //Genera un constructor con todos los atributos
public class Categoria {

    /**
     * Identificador único.
     * GenerationType.IDENTITY hace que sea 'identity' en la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombre: obligatorio (nullable=false), único y máx 100 caracteres.
     */
    @Column(nullable = false, unique = true, length = 100)
    private String nombre;

    /**
     * Descripción: máximo 2000 caracteres.
     */
    @Column(length = 2000)
    private String descripcion;

    /**
     * Imagen: máximo 500 caracteres.
     */
    @Column(length = 500)
    private String imagen;

    // Relación con productos: Varias categorías pueden tener varios productos
    @ManyToMany(mappedBy = "categorias")
    private List<Producto> productos;

    @Override
    public String toString() {
        return "Categoria [id=" + id + ", nombre=" + nombre + "]";
    }
}