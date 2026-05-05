package es.iesclaradelrey.da2d1a.tiendadrmjcgt.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Configuración de infraestructura de seguridad.
 * Define beans reutilizables relacionados con algoritmos de cifrado y hashing
 * que son independientes de la cadena de filtros de red.
 */
@Configuration
public class SecurityConfig {

    /**
     * Define el algoritmo de codificación de contraseñas para la aplicación.
     * Utiliza BCrypt con un factor de fuerza (strength) de 12 para asegurar una
     * protección robusta contra ataques de fuerza bruta.
     * @return Una instancia de BCryptPasswordEncoder configurada.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}