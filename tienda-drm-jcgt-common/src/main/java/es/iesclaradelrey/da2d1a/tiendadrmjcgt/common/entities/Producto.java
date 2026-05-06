package es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Representa un artículo a la venta dentro de la tienda.
 * <p>
 * Esta clase emplea anotaciones de Lombok para gestionar de forma automática
 * el estado del objeto y su integración con el motor de persistencia JPA.
 * </p>
 */
@Entity // Define la clase como una entidad gestionada por JPA
@Table(name = "productos") // Especifica el nombre de la tabla física en la base de datos
@Data // Genera getters, setters, toString, equals y hashCode automáticamente
@NoArgsConstructor // Proporciona el constructor vacío requerido por Hibernate
@AllArgsConstructor // Genera un constructor con todos los atributos de la clase
public class Producto {

    /**
     * Identificador único autoincremental en la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Código de barras EAN-13: campo obligatorio con longitud fija de 13 caracteres.
     */
    @Column(nullable = false, length = 13)
    private String codigoEan;

    /**
     * Nombre comercial: obligatorio y limitado a 200 caracteres.
     */
    @Column(nullable = false, length = 200)
    private String nombre;

    /**
     * Descripción detallada del artículo: obligatoria, soporta hasta 4000 caracteres.
     */
    @Column(nullable = false, length = 4000)
    private String descripcion;

    /**
     * Ruta relativa de la imagen: campo opcional con máximo de 500 caracteres.
     */
    @Column(length = 500)
    private String imagen;

    /**
     * Valor monetario unitario del producto: campo obligatorio.
     */
    @Column(nullable = false)
    private Double precio;

    /**
     * Porcentaje de reducción de precio (valor entre 0 y 99): obligatorio.
     */
    @Column(nullable = false)
    private Integer descuento;

    @Column(nullable = false)
    private Integer stock;

    /**
     * Relación con la marca: cada producto pertenece obligatoriamente a un fabricante.
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_marca") // Define la clave foránea en la tabla productos
    private Marca marca;

    /**
     * Relación con categorías: permite la clasificación del producto en múltiples secciones.
     * Define una tabla intermedia para gestionar la relación muchos a muchos.
     */
    @ManyToMany
    @JoinTable(
            name = "productos_categorias",
            joinColumns = @JoinColumn(name = "id_producto"),
            inverseJoinColumns = @JoinColumn(name = "id_categoria")
    )
    private List<Categoria> categorias;

    /**
     * Representación textual personalizada para evitar ciclos de recursión con relaciones.
     */
    @Override
    public String toString() {
        return "Producto [id=" + id + ", nombre=" + nombre + "]";
    }
}