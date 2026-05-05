package es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Entidad que representa a un usuario del sistema.
 * Almacena información personal, credenciales de acceso y la relación con sus roles asignados.
 */
@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    /**
     * Identificador único autogenerado para el usuario.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombre de usuario único utilizado para la autenticación.
     */
    @Column(nullable = false, unique = true)
    private String username;

    /**
     * Contraseña cifrada del usuario.
     */
    @Column(nullable = false)
    private String password;

    /**
     * Nombre de pila del usuario.
     */
    @Column(nullable = false)
    private String nombre;

    /**
     * Apellidos del usuario.
     */
    @Column(nullable = false)
    private String apellidos;

    /**
     * Dirección de correo electrónico única para contacto y recuperación de cuenta.
     */
    @Column(nullable = false, unique = true)
    private String email;

    /**
     * Número de teléfono de contacto (opcional).
     */
    @Column(nullable = true)
    private String telefono;

    /**
     * Fecha de nacimiento del usuario para validaciones de edad.
     */
    @Column(nullable = true)
    private LocalDate fechaNacimiento;

    /**
     * Estado de la cuenta. Si es falso, el usuario no podrá acceder al sistema.
     */
    @Column(nullable = false)
    private Boolean activo = true;

    /**
     * Fecha y hora en la que el usuario se registró en la plataforma.
     * Este campo no es modificable tras su creación.
     */
    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaRegistro;

    /**
     * Conjunto de roles asignados al usuario que definen sus permisos.
     * Se cargan de forma impaciente (EAGER) para facilitar la autorización.
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "usuarios_roles",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id")
    )
    private Set<Rol> roles = new HashSet<>();

    /**
     * Método de ciclo de vida ejecutado antes de persistir el usuario.
     * Inicializa la fecha de registro y asegura el estado activo por defecto.
     */
    @PrePersist
    protected void onCreate() {
        this.fechaRegistro = LocalDateTime.now();
        if (this.activo == null) {
            this.activo = true;
        }
    }
}