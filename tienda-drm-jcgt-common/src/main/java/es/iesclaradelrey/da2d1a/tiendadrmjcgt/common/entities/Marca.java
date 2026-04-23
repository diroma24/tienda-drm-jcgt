package es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

/**
 * Representa un fabricante o marca comercial de los productos de la tienda.
 * <p>
 * Esta clase utiliza anotaciones de Lombok para la generación de métodos
 * de acceso y constructores, facilitando la integración con Hibernate.
 * </p>
 */
@Entity // Vincula la clase a una entidad de persistencia
@Table(name = "marcas") // Define el nombre de la tabla en la base de datos
@Getter // Genera automáticamente los métodos de lectura para los atributos
@Setter // Genera automáticamente los métodos de escritura para los atributos
@NoArgsConstructor // Proporciona el constructor sin parámetros requerido por JPA
@AllArgsConstructor // Proporciona un constructor con todos los campos de la clase
public class Marca {

    /**
     * Identificador único autoincremental.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombre de la marca: obligatorio y único en el sistema.
     */
    @Column(nullable = false, unique = true)
    private String nombre;

    /**
     * Información detallada sobre la trayectoria o especialidad de la marca.
     */
    private String descripcion;

    /**
     * Ruta o identificador de la imagen/logotipo de la marca.
     */
    private String imagen;

    /**
     * Relación inversa: lista de productos fabricados por esta marca.
     * Mapeado por el atributo "marca" en la entidad Producto.
     */
    @OneToMany(mappedBy = "marca")
    private List<Producto> productos;

    /**
     * Devuelve una representación textual simplificada para evitar recursión.
     */
    @Override
    public String toString() {
        return "Marca [id=" + id + ", nombre=" + nombre + "]";
    }
}