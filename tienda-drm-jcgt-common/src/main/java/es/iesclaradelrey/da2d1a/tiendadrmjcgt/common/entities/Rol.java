package es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad que representa los roles de usuario dentro del sistema.
 * Define los diferentes niveles de permisos o perfiles asignables a los usuarios.
 */
@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rol {

    /**
     * Identificador único del rol.
     * Consiste en un código alfanumérico de hasta 6 caracteres (e.g., 'ADMIN', 'USER').
     */
    @Id
    @Column(length = 6)
    private String id;

    /**
     * Descripción detallada de las funciones o permisos que abarca el rol.
     * Limitada a un máximo de 100 caracteres.
     */
    @Column(nullable = false, length = 100)
    private String descripcion;
}