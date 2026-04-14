package es.iesclaradelrey.da2d1a.tiendadrmjcgt.web.controllers;

import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities.Categoria;
import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.services.CategoriaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Optional;

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

    /**
     * Obtiene y muestra los detalles de una categoría específica basada en su identificador.
     * <p>
     * Este método maneja las peticiones HTTP GET a la ruta {@code /{id}}. Utiliza el servicio
     * correspondiente para buscar la categoría solicitada. Si la categoría existe, la añade
     * al {@link org.springframework.ui.Model} para que la vista pueda renderizar sus datos.
     * Si el identificador proporcionado no corresponde a ninguna categoría existente en la
     * base de datos, redirige automáticamente al usuario a la página del listado general.
     * </p>
     *
     * @param id el identificador único de la categoría a buscar, extraído directamente
     * de la variable de ruta de la URL.
     * @param model el contenedor de datos ({@link org.springframework.ui.Model}) utilizado
     * para pasar el objeto de la categoría desde el controlador a la vista Thymeleaf.
     * @return un {@link String} que representa la vista a renderizar ({@code categorias/detalle})
     * en caso de éxito, o una directiva de redirección ({@code redirect:/categorias})
     * en caso de no encontrar el registro.
     */
    @GetMapping("/{id}")
    public String detalleCategoria(@PathVariable("id") Long id, Model model) {
        // Busca la categoría usando optional (puede no existir la categoria con el id especificado)
        Optional<Categoria> categoriaOpt = categoriaService.getById(id);

        if (categoriaOpt.isPresent()) {
            // Si existe, se pasa a la vista
            model.addAttribute("categoria", categoriaOpt.get());
            return "categorias/detalle"; // Nombre del archivo HTML
        } else {
            // Si el usuario pone un ID que no existe (ej: /categorias/peluches), se vuelve al listado
            return "redirect:/categorias";
        }
    }
}