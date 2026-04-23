package es.iesclaradelrey.da2d1a.tiendadrmjcgt.web.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controlador principal del panel de administración.
 * <p>
 * Gestiona el acceso al área restringida y normaliza las rutas de navegación
 * mediante redirecciones para asegurar la consistencia en las URLs de gestión.
 * </p>
 */
@Controller // Define la clase como controlador para el área de administración
@RequestMapping("/admin") // Prefijo de ruta para todas las operaciones de administración
public class AdminController {

    /**
     * Procesa la petición de acceso al panel principal de administración.
     * @return Nombre de la plantilla de inicio para administradores.
     */
    @GetMapping("")
    public String inicio() {
        return "admin/inicio";
    }

    /**
     * Normaliza la ruta raíz de administración eliminando la barra final.
     * @return Redirección a la ruta base de administración.
     */
    @GetMapping("/")
    public String redireccionInicio() {
        return "redirect:/admin";
    }

    /**
     * Normaliza la ruta del listado de productos.
     * @return Redirección a la ruta estandarizada de productos.
     */
    @GetMapping("/productos/")
    public String redireccionProductos() {
        return "redirect:/admin/productos";
    }

    /**
     * Normaliza la ruta del listado de categorías.
     * @return Redirección a la ruta estandarizada de categorías.
     */
    @GetMapping("/categorias/")
    public String redireccionCategorias() {
        return "redirect:/admin/categorias";
    }

    /**
     * Normaliza la ruta del listado de marcas.
     * @return Redirección a la ruta estandarizada de marcas.
     */
    @GetMapping("/marcas/")
    public String redireccionMarcas() {
        return "redirect:/admin/marcas";
    }
}