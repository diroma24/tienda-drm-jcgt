package es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

/**
 * Entidad que representa una marca de la tienda.
 */
@Entity
@Table(name = "marcas")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Marca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre;

    private String descripcion;
    private String imagen;

    // Relación Inversa - una marca fabrica muchos productos
    @OneToMany(mappedBy = "marca")
    private List<Producto> productos;
}