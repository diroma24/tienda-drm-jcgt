package es.iesclaradelrey.da2d1a.tiendadrmjcgt.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controlador encargado de gestionar las peticiones relacionadas con la autenticación.
 * Proporciona los puntos de entrada para la visualización del formulario de acceso.
 */
@Controller
public class LoginController {

    /**
     * Gestiona la petición GET para mostrar la página de inicio de sesión.
     * @return El nombre de la vista (plantilla) del formulario de login.
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }
}