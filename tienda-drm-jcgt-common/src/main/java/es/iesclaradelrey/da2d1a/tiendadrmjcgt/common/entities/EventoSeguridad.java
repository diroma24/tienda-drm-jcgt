package es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "eventos_seguridad")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventoSeguridad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime fechaHora; // Fecha y hora del evento

    @Column(nullable = false)
    private String username; // Nombre de usuario del evento

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoEventoSeguridad tipoEvento; // Tipo de evento (usando el enum)

    // Atributo extra útil: Dirección IP desde la que se realiza la acción
    private String ip;
}