package es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.repositories;

import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repositorio de acceso a datos para la entidad Usuario.
 * Proporciona métodos estándar de CRUD y búsquedas personalizadas sobre la tabla de usuarios.
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    /**
     * Busca un usuario en la base de datos a partir de su nombre de usuario.
     * * @param username El nombre de usuario que se desea buscar.
     * @return Un Optional que contiene el usuario si se encuentra, o vacío en caso contrario.
     */
    Optional<Usuario> findByUsername(String username);
}