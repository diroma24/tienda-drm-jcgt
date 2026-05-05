package es.iesclaradelrey.da2d1a.tiendadrmjcgt.security;

import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities.Usuario;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Adaptador para la entidad Usuario que implementa la interfaz UserDetails de Spring Security.
 * Permite que el sistema de seguridad gestione la autenticación y autorización utilizando
 * los datos de nuestra entidad de negocio.
 */
@Getter
public class UsuarioSecurity implements UserDetails {

    /**
     * Instancia de la entidad de usuario original de la base de datos.
     */
    private final Usuario usuario;

    /**
     * Constructor que envuelve la entidad Usuario para su uso en el contexto de seguridad.
     * @param usuario La entidad de usuario a adaptar.
     */
    public UsuarioSecurity(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * Convierte los roles asignados al usuario en una colección de autoridades otorgadas.
     * Añade el prefijo 'ROLE_' a cada identificador de rol para cumplir con la convención de Spring Security.
     * @return Colección de autoridades (permisos) del usuario.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return usuario.getRoles().stream()
                .map(rol -> new SimpleGrantedAuthority("ROLE_" + rol.getId()))
                .collect(Collectors.toList());
    }

    /**
     * Obtiene la contraseña almacenada del usuario.
     * @return La contraseña cifrada.
     */
    @Override public String getPassword() { return usuario.getPassword(); }

    /**
     * Obtiene el nombre de usuario (username) para la identificación.
     * @return El nombre de usuario único.
     */
    @Override public String getUsername() { return usuario.getUsername(); }

    /**
     * Indica si la cuenta del usuario ha expirado.
     * @return Siempre true en esta implementación.
     */
    @Override public boolean isAccountNonExpired() { return true; }

    /**
     * Indica si el usuario está bloqueado o no.
     * @return Siempre true en esta implementación.
     */
    @Override public boolean isAccountNonLocked() { return true; }

    /**
     * Indica si las credenciales (contraseña) han expirado.
     * @return Siempre true en esta implementación.
     */
    @Override public boolean isCredentialsNonExpired() { return true; }

    /**
     * Indica si el usuario está habilitado para acceder al sistema.
     * Se basa en el atributo 'activo' de la entidad Usuario.
     * @return true si el usuario está activo, false en caso contrario.
     */
    @Override public boolean isEnabled() { return usuario.getActivo(); }
}