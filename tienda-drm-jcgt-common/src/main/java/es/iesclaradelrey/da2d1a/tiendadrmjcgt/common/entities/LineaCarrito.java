package es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "lineas_carrito")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class LineaCarrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Cada usuario tiene asociado su carro a través de estas líneas
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    // Producto añadido al carro
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    // Número de unidades que se desean
    @Column(nullable = false)
    private Integer unidades;

    // Fecha y hora en la que se añadió o actualizó
    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    // Se actualiza automáticamente antes de guardar o editar[cite: 1]
    @PrePersist
    @PreUpdate
    protected void onUpdate() {
        fechaActualizacion = LocalDateTime.now();
    }
}