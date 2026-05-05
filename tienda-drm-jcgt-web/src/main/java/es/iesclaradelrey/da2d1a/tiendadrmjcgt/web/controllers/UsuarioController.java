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

/**
 * Controlador encargado de la gestión de perfiles de usuario y visualización de datos personales.
 * Implementa controles de seguridad basados en roles (RBAC) y propiedad de cuenta.
 */
@Controller
@RequestMapping("/users")
public class UsuarioController {

    /**
     * Repositorio para la consulta de información de usuarios en la base de datos.
     */
    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Muestra el perfil del usuario que ha iniciado sesión actualmente.
     * @param model El modelo de la vista para añadir los datos del usuario.
     * @param usuarioLogueado Objeto de seguridad que representa al usuario autenticado.
     * @return El nombre de la vista de perfil.
     */
    @GetMapping("/profile")
    public String verPerfilPropio(Model model, @AuthenticationPrincipal UsuarioSecurity usuarioLogueado) {
        model.addAttribute("usuario", usuarioLogueado.getUsuario());
        return "perfil";
    }

    /**
     * Muestra el perfil de un usuario específico identificado por su ID.
     * Aplica lógica de seguridad: solo el propio usuario o un administrador pueden acceder.
     * @param userId Identificador único del usuario cuyo perfil se desea consultar.
     * @param model El modelo de la vista para añadir la información del usuario consultado.
     * @param usuarioLogueado Objeto de seguridad que representa al usuario autenticado.
     * @return El nombre de la vista de perfil o redirección en caso de acceso denegado.
     * @throws RuntimeException Si el usuario solicitado no existe en el sistema.
     */
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
                . anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        // Comprobar si el logueado es el dueño de la cuenta
        boolean esElMismoUsuario = usuarioLogueado.getUsuario().getId().equals(userId);

        // Si no es ninguno de los dos, denegamos el acceso
        if (!isAdmin && !esElMismoUsuario) {
            return "redirect:/login?error=acceso-denegado";
        }

        // Si tiene permiso, cargamos la vista con el usuario solicitado
        model.addAttribute("usuario", usuarioConsultado);
        return "perfil";
    }
}