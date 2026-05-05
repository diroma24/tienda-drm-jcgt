package es.iesclaradelrey.da2d1a.tiendadrmjcgt.web.controllers;

import es.iesclaradelrey.da2d1a.tiendadrmjcgt.security.UsuarioSecurity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UsuarioController {

    @GetMapping("/profile")
    public String verPerfil(Model model, @AuthenticationPrincipal UsuarioSecurity usuarioLogueado) {
        // Pasamos el objeto usuario de la entidad al modelo
        model.addAttribute("usuario", usuarioLogueado.getUsuario());
        return "perfil";
    }
}