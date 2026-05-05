package es.iesclaradelrey.da2d1a.tiendadrmjcgt.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Configuración principal de seguridad web de la aplicación.
 * Define las reglas de acceso, políticas de cifrado, gestión de sesiones y
 * personalización de los procesos de inicio y cierre de sesión.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    /**
     * Configura la cadena de filtros de seguridad (Security Filter Chain).
     * Establece los permisos por URL, la protección contra CSRF y el comportamiento
     * de los formularios de autenticación.
     * * @param http Objeto para configurar la seguridad basada en web para solicitudes HTTP específicas.
     * @return La cadena de filtros configurada.
     * @throws Exception Si ocurre algún error durante la configuración.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Configuración para permitir el uso de frames en el mismo origen (útil para consolas de DB)
                .headers(headers -> headers
                        .frameOptions(frame -> frame.sameOrigin())
                )

                // 1. REGLAS DE AUTORIZACIÓN: Define quién puede acceder a cada recurso
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/register", "/login", "/css/**", "/js/**", "/img/**").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/users/profile/**").authenticated()
                        .anyRequest().permitAll()
                )

                // Protección CSRF: Se ignora específicamente para la consola H2
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**"))
                )

                // 2. PERSONALIZACIÓN DEL LOGIN: Configura el formulario y rutas de éxito/fallo
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/", true)
                        .failureUrl("/login?error=true")
                        .permitAll()
                )

                // 3. PERSONALIZACIÓN DEL LOGOUT: Define la salida del sistema y limpieza de cookies
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                )

                // Desactiva la autenticación básica de HTTP
                .httpBasic(basic -> basic.disable());

        return http.build();
    }
}