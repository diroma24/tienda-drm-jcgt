package es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Entidad que representa un producto de la tienda.
 */
@Entity
@Table(name = "productos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

    /**
     * 1) Identificador único asignado automáticamente por la base de datos (patrón identity)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 2) Código EAN-13 (13 dígitos). Obligatorio
     */
    @Column(nullable = false, length = 13)
    private String codigoEan;

    /**
     * 3) Nombre del producto. Obligatorio. Máximo 200 caracteres
     */
    @Column(nullable = false, length = 200)
    private String nombre;

    /**
     * 4) Descripción del producto. Obligatoria. Máximo 4000 caracteres
     */
    @Column(nullable = false, length = 4000)
    private String descripcion;

    /**
     * 5) URL de la imagen relativa a la raíz. Opcional. Máximo 500 caracteres
     */
    @Column(length = 500)
    private String imagen;

    /**
     * 6) Precio del producto. Obligatorio. Se usa Double por simplicidad
     */
    @Column(nullable = false)
    private Double precio;

    /**
     * 7) Descuento (0-99). Obligatorio
     */
    @Column(nullable = false)
    private Integer descuento;

    // Relación con marca - Muchos productos pertenecen a una marca
    @ManyToOne(optional = false) // obligatorio
    @JoinColumn(name = "id_marca")
    private Marca marca;

    // Relación con categorías: Muchos productos pueden estar en muchas categorías
    @ManyToMany
    @JoinTable(
            name = "productos_categorias",
            joinColumns = @JoinColumn(name = "id_producto"),
            inverseJoinColumns = @JoinColumn(name = "id_categoria")
    )
    private List<Categoria> categorias;

    @Override
    public String toString() {
        return "Producto [id=" + id + ", nombre=" + nombre + "]";
    }
}