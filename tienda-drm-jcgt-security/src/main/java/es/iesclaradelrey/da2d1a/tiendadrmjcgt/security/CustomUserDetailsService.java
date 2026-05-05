package es.iesclaradelrey.da2d1a.tiendadrmjcgt.security;

import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities.Usuario;
import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Servicio personalizado para la carga de detalles de usuario dentro de Spring Security.
 * Implementa la interfaz UserDetailsService para conectar la base de datos con el sistema de autenticación.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    /**
     * Servicio de lógica de negocio para la gestión de usuarios.
     */
    private final UsuarioService usuarioService;

    /**
     * Constructor para la inyección de dependencias del servicio de usuarios.
     * @param usuarioService Servicio que proporciona el acceso a los datos de los usuarios.
     */
    @Autowired
    public CustomUserDetailsService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /**
     * Localiza al usuario en la base de datos basándose en el nombre de usuario introducido en el login.
     * @param username El nombre de usuario que intenta autenticarse.
     * @return Una instancia de UserDetails (UsuarioSecurity) compatible con Spring Security.
     * @throws UsernameNotFoundException Si no existe ningún usuario con el nombre proporcionado.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioService.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("No se ha encontrado el usuario: " + username));

        return new UsuarioSecurity(usuario);
    }
}