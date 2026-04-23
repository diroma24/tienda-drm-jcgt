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

/**
 * Controlador para la gestión de la navegación pública por categorías.
 * <p>
 * Se encarga de exponer los datos de las categorías y sus productos asociados
 * a los usuarios finales de la tienda.
 * </p>
 */
@Controller // Define la clase como un controlador de Spring MVC
@RequestMapping("/categorias") // Establece el prefijo de ruta para todas las peticiones del controlador
public class CategoriaController {

    private final CategoriaService categoriaService;

    /**
     * Constructor para la inyección del servicio de categorías.
     */
    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    /**
     * Recupera y muestra el catálogo completo de categorías disponibles.
     * Envía la colección de categorías a la vista de listado público.
     * * @param model Objeto de Spring para el paso de datos a la vista.
     * @return Nombre de la plantilla para el listado de categorías.
     */
    @GetMapping({"", "/"})
    public String listado(Model model) {
        model.addAttribute("listaCategorias", categoriaService.findAll());
        return "categorias/listado";
    }

    /**
     * Muestra el detalle de una categoría específica y sus productos relacionados.
     * Los productos se presentan ordenados alfabéticamente de forma insensible a mayúsculas.
     * * @param id Identificador único de la categoría.
     * @param model Objeto de Spring para el paso de datos a la vista.
     * @return Nombre de la plantilla de detalle o redirección si la categoría no existe.
     */
    @GetMapping("/{id}")
    public String detalle(@PathVariable Long id, Model model) {
        Categoria categoria = categoriaService.findById(id);

        if (categoria == null) {
            return "redirect:/categorias";
        }

        // Procesa la colección de productos para aplicar el criterio de ordenación nominal
        List<Producto> productosOrdenados = new ArrayList<>(categoria.getProductos());
        productosOrdenados.sort(Comparator.comparing(Producto::getNombre, String.CASE_INSENSITIVE_ORDER));

        model.addAttribute("categoria", categoria);
        model.addAttribute("productosDeLaCategoria", productosOrdenados);

        return "categorias/detalle";
    }
}