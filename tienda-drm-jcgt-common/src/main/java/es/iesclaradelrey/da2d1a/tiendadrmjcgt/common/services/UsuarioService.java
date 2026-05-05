package es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.services;

import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities.Usuario;
import java.util.Optional;

/**
 * Interfaz que define los servicios de gestión de usuarios.
 * Proporciona los métodos necesarios para la lógica de negocio relacionada con la administración de cuentas.
 */
public interface UsuarioService {

    /**
     * Recupera un usuario del sistema basándose en su nombre de usuario.
     * @param username El nombre de usuario que se desea buscar.
     * @return Un Optional que contiene el usuario si existe, o vacío en caso contrario.
     */
    Optional<Usuario> findByUsername(String username);
}