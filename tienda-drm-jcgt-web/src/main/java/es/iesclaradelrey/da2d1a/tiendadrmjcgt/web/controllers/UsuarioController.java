package es.iesclaradelrey.da2d1a.tiendadrmjcgt.web.controllers;

import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities.Usuario;
import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.repositories.UsuarioRepository;
import es.iesclaradelrey.da2d1a.tiendadrmjcgt.security.UsuarioSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Perfil del usuario
    @GetMapping("/profile")
    public String verPerfilPropio(Model model, @AuthenticationPrincipal UsuarioSecurity usuarioLogueado) {
        model.addAttribute("usuario", usuarioLogueado.getUsuario());
        return "perfil";
    }

    // Perfil por ID con seguridad RBAC
    @GetMapping("/profile/{userId}")
    public String verPerfilPorId(@PathVariable Long userId,
                                 Model model,
                                 @AuthenticationPrincipal UsuarioSecurity usuarioLogueado) {

        // Se busca el usuario deseado
        Usuario usuarioConsultado = usuarioRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Lógica de Seguridad RBAC:
        // Comprobar si el logueado es ADMIN
        boolean isAdmin = usuarioLogueado.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        // Comprobar si el logueado es el dueño de la cuenta
        boolean esElMismoUsuario = usuarioLogueado.getUsuario().getId().equals(userId);

        // Si no es ninguno de los dos, denegamos el acceso
        if (!isAdmin && !esElMismoUsuario) {
            // Redirigimos o lanzamos error (Spring Security manejará el acceso denegado)
            return "redirect:/login?error=acceso-denegado";
        }

        // Si tiene permiso, cargamos la vista con el usuario solicitado
        model.addAttribute("usuario", usuarioConsultado);
        return "perfil";
    }
}