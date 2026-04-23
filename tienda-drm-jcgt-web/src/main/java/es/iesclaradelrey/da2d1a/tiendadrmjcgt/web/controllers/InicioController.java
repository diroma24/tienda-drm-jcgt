package es.iesclaradelrey.da2d1a.tiendadrmjcgt.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controlador de entrada principal a la aplicación.
 * <p>
 * Gestiona las peticiones dirigidas a la raíz del sitio web, coordinando
 * el acceso a la página de inicio del portal.
 * </p>
 */
@Controller // Indica que la clase actúa como controlador web y retorna vistas lógicas
public class InicioController {

    /**
     * Procesa la petición de acceso a la URL raíz del sistema.
     * <p>
     * Resuelve la navegación hacia la plantilla dinámica de inicio, evitando
     * conflictos con recursos estáticos predeterminados.
     * </p>
     * * @return Nombre de la vista "home" que será procesada por el motor de plantillas.
     */
    @GetMapping("/")
    public String index() {
        // Retorna la vista dinámica home.html ubicada en la carpeta de plantillas (templates)
        return "home";
    }
}