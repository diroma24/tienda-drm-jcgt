package es.iesclaradelrey.da2d1a.tiendadrmjcgt.web.controllers;

import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities.Categoria;
import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities.Producto;
import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.services.CategoriaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
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
    public String detalle(@PathVariable Long id, Model model) {
        // getById devuelve Optional, usamos .orElse(null)
        Categoria categoria = categoriaService.getById(id).orElse(null);

        if (categoria == null) {
            return "redirect:/categorias";
        }

        // Usa la propiedad de navegación categoria.getProductos()
        List<Producto> productosOrdenados = new ArrayList<>(categoria.getProductos());

        // Ordenación alfabética ignorando mayúsculas/minúsculas
        productosOrdenados.sort(Comparator.comparing(Producto::getNombre, String.CASE_INSENSITIVE_ORDER));

        model.addAttribute("categoria", categoria);
        model.addAttribute("productosDeLaCategoria", productosOrdenados);

        return "categorias/detalle";
    }
}