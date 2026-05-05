package es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.services;

import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities.Usuario;
import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implementación del servicio de gestión de usuarios.
 * Contiene la lógica de negocio para la administración de cuentas y la interacción
 * con el repositorio de usuarios.
 */
@Service
public class UsuarioServiceImpl implements UsuarioService {

    /**
     * Repositorio para el acceso y gestión de la persistencia de los usuarios.
     */
    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Recupera un usuario del sistema delegando la búsqueda en el repositorio.
     * @param username El nombre de usuario que se desea buscar.
     * @return Un Optional que contiene el usuario si existe, o vacío en caso contrario.
     */
    @Override
    public Optional<Usuario> findByUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }
}