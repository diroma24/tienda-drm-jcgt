package es.iesclaradelrey.da2d1a.tiendadrmjcgt.web.dtos;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;

/**
 * Objeto de transferencia de datos (DTO) para el registro de nuevos usuarios.
 * Contiene los campos necesarios para el formulario de alta y las reglas de validación correspondientes.
 */
@Data
public class UsuarioRegistroDto {

    /**
     * Nombre de usuario único elegido por el cliente.
     * Debe tener entre 4 y 20 caracteres.
     */
    @NotBlank(message = "El nombre de usuario es obligatorio")
    @Size(min = 4, max = 20, message = "El usuario debe tener entre 4 y 20 caracteres")
    private String username;

    /**
     * Contraseña en texto plano antes de ser cifrada por el servicio.
     * Requiere una longitud mínima de 6 caracteres.
     */
    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    private String password;

    /**
     * Nombre de pila del interesado.
     */
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    /**
     * Apellidos del interesado.
     */
    @NotBlank(message = "Los apellidos son obligatorios")
    private String apellidos;

    /**
     * Dirección de correo electrónico de contacto.
     * Debe cumplir con un formato de email válido.
     */
    @NotBlank(message = "El email es obligatorio")
    @Email(message = "Debe ser un email válido")
    private String email;

    /**
     * Número de teléfono de contacto (opcional).
     */
    private String telefono;

    /**
     * Fecha de nacimiento para verificar la edad del usuario.
     * Debe ser una fecha anterior a la actual.
     */
    @Past(message = "La fecha de nacimiento debe ser pasada")
    private LocalDate fechaNacimiento;

    /**
     * Campo de validación para asegurar la aceptación de los términos legales del sitio.
     */
    @AssertTrue(message = "Debes aceptar las condiciones para registrarte")
    private boolean aceptoCondiciones;
}