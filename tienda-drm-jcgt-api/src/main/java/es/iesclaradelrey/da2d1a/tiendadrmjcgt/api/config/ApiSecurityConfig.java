package es.iesclaradelrey.da2d1a.tiendadrmjcgt.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ApiSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Desactiva CSRF (recomendable para ir preparando el terreno de las API REST)
                .csrf(csrf -> csrf.disable())
                // Permite el acceso a cualquier petición sin autenticación
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()
                );

        return http.build();
    }
}