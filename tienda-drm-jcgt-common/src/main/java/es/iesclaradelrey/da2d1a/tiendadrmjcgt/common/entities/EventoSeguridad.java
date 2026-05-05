package es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * Entidad que representa un evento de seguridad dentro del sistema.
 * Almacena registros de auditoría como inicios de sesión, intentos fallidos y acciones críticas.
 */
@Entity
@Table(name = "eventos_seguridad")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventoSeguridad {

    /**
     * Identificador único autogenerado para el evento de seguridad.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Fecha y hora exacta en la que se produjo el evento.
     */
    @Column(nullable = false)
    private LocalDateTime fechaHora;

    /**
     * Nombre de usuario asociado a la acción que generó el evento.
     */
    @Column(nullable = false)
    private String username;

    /**
     * Categoría o tipo de evento de seguridad (e.g., LOGIN_EXITOSO, LOGIN_FALLIDO).
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoEventoSeguridad tipoEvento;

    /**
     * Dirección IP de origen desde la cual se realizó la petición o acción.
     */
    private String ip;
}