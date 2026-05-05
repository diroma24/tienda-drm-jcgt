package es.iesclaradelrey.da2d1a.tiendadrmjcgt.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Configuración central de seguridad web para la aplicación.
 * Define las reglas de acceso, políticas de frames y gestión de autenticación.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    /**
     * Define la cadena de filtros de seguridad (Security Filter Chain).
     *
     * @param http Objeto para configurar la seguridad basada en peticiones HTTP.
     * @return El filtro de seguridad construido.
     * @throws Exception Si ocurre un error durante la configuración.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Permite el uso de frames solo si el origen es el mismo (necesario para la consola H2)
                .headers(headers -> headers
                        .frameOptions(frame -> frame.sameOrigin())
                )

                // Define qué rutas requieren autenticación y cuáles son públicas
                .authorizeHttpRequests(auth -> auth
                        // Restringe el acceso a la consola H2 y al panel de administración
                        .requestMatchers(new AntPathRequestMatcher("/h2-console/**")).authenticated()
                        .requestMatchers(new AntPathRequestMatcher("/admin/**")).authenticated()
                        // El resto de la tienda es de acceso libre
                        .anyRequest().permitAll()
                )

                // Desactiva la protección CSRF específicamente para H2 para evitar bloqueos en su consola
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**"))
                )

                // Habilita el formulario de login estándar de Spring Security
                .formLogin(form -> form
                        .permitAll()
                )

                // Desactiva la autenticación HTTP Basic
                .httpBasic(basic -> basic.disable());

        return http.build();
    }
}