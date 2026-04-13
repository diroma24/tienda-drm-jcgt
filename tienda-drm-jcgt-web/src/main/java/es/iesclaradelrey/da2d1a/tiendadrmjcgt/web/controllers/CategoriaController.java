package es.iesclaradelrey.da2d1a.tiendadrmjcgt.web.controllers;

import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.services.CategoriaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controlador encargado de gestionar las peticiones web relacionadas con las categorías.
 * <p>
 * Se encarga de recibir las solicitudes del usuario, interactuar con el {@link CategoriaService}
 * y devolver la vista correspondiente para su visualización en el navegador.
 * </p>
 */
@Controller
@RequestMapping("/categorias") // Atiende la URL base /categorias
public class CategoriaController {

    private final CategoriaService categoriaService;

    // Inyección por constructor de la interfaz del servicio
    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    // Responde a /categorias y /categorias/
    @GetMapping({"", "/"})
    public String listado(Model model) {
        // Se obtiene la colección de categorías desde el servicio
        // Se añade al modelo con el nombre "listaCategorias"
        model.addAttribute("listaCategorias", categoriaService.getAll());

        // Se devuele el nombre de la vista
        return "categorias/listado-categorias";
    }
}