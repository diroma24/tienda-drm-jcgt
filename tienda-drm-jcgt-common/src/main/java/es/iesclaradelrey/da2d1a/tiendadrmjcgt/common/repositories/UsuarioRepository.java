package es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.repositories;

import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsername(String username);
}