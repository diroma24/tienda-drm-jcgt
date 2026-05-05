package es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.services;

import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities.Usuario;
import java.util.Optional;

public interface UsuarioService {
    Optional<Usuario> findByUsername(String username);
}

