package es.iesclaradelrey.da2d1a.tiendadrmjcgt.api.controllers;

import es.iesclaradelrey.da2d1a.tiendadrmjcgt.api.dtos.*;
import es.iesclaradelrey.da2d1a.tiendadrmjcgt.api.services.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationRestController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
        try {
            // Validar credenciales usando AuthenticationManager
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );

            // Si tiene éxito, buscamos al usuario y generamos tokens
            UserDetails user = userDetailsService.loadUserByUsername(request.getUsername());
            String access = jwtService.generateAccessToken(user);
            String refresh = jwtService.generateRefreshToken(user);

            return ResponseEntity.ok(new AuthenticationResponse(access, refresh));

        } catch (AuthenticationException e) {
            // Si falla, devolvemos 401
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    @PostMapping("/refresh")
    public ResponseEntity<AuthenticationResponse> refresh(@RequestBody TokenRefreshRequest request) {
        String refreshToken = request.getRefreshToken();
        String username = jwtService.extractUsername(refreshToken);

        if (username != null) {
            UserDetails user = userDetailsService.loadUserByUsername(username);

            // Validar si el token de refresco es válido
            if (jwtService.isTokenValid(refreshToken, user)) {
                String newAccess = jwtService.generateAccessToken(user);
                // Devolvemos el nuevo access token y el mismo refresh token[cite: 1]
                return ResponseEntity.ok(new AuthenticationResponse(newAccess, refreshToken));
            }
        }

        // Si el token no es válido, devolvemos 400 Bad Request[cite: 1]
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
