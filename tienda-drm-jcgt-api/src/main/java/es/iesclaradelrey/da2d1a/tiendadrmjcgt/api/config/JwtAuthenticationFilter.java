package es.iesclaradelrey.da2d1a.tiendadrmjcgt.api.config;

import es.iesclaradelrey.da2d1a.tiendadrmjcgt.api.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String username;

        // 1. Comprobar cabecera Authorization y que empiece por "Bearer "
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 2. Extraer el token (quitando "Bearer ")
        jwt = authHeader.substring(7);

        try {
            // 3. Extraer nombre de usuario
            username = jwtService.extractUsername(jwt);

            // 4. Comprobar que el usuario no está ya autenticado
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                // 5. Buscar usuario en la base de datos
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

                // 6. Verificar si el token es válido para ese usuario
                if (jwtService.isTokenValid(jwt, userDetails)) {

                    // 7. Crear el objeto Authentication
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    // 8. Fijarlo en el contexto de seguridad[cite: 1]
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        } catch (Exception e) {
            // Si hay error (token manipulado, etc.), pasamos el testigo sin autenticar[cite: 1]
        }

        // Siempre pasar al siguiente filtro[cite: 1]
        filterChain.doFilter(request, response);
    }
}
