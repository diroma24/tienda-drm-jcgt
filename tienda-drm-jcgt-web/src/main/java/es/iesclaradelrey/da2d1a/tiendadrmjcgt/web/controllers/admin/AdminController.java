package es.iesclaradelrey.da2d1a.tiendadrmjcgt.web.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    //Página inicial de administración
    @GetMapping("")
    public String inicio() {
        return "admin/inicio";
    }

    //Redirección obligatoria si se escribe /admin/
    @GetMapping("/")
    public String redireccionInicio() {
        return "redirect:/admin";
    }

    //Métodos para gestionar las redirecciones de los listados (con barra a sin barra)
    @GetMapping("/productos/")
    public String redireccionProductos() {
        return "redirect:/admin/productos";
    }

    @GetMapping("/categorias/")
    public String redireccionCategorias() {
        return "redirect:/admin/categorias";
    }

    @GetMapping("/marcas/")
    public String redireccionMarcas() {
        return "redirect:/admin/marcas";
    }
}