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
 * Controlador para la parte pública de categorías.
 */
@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping({"", "/"})
    public String listado(Model model) {
        // CORRECCIÓN: Cambiado getAll() por findAll() para coincidir con la nueva interfaz
        model.addAttribute("listaCategorias", categoriaService.findAll());
        return "categorias/listado-categorias";
    }

    @GetMapping("/{id}")
    public String detalle(@PathVariable Long id, Model model) {
        // CORRECCIÓN: Cambiado getById(id).orElse(null) por findById(id)
        // ya que ahora nuestro service devuelve directamente el objeto o null
        Categoria categoria = categoriaService.findById(id);

        if (categoria == null) {
            return "redirect:/categorias";
        }

        // Obtener productos y ordenar
        List<Producto> productosOrdenados = new ArrayList<>(categoria.getProductos());
        productosOrdenados.sort(Comparator.comparing(Producto::getNombre, String.CASE_INSENSITIVE_ORDER));

        model.addAttribute("categoria", categoria);
        model.addAttribute("productosDeLaCategoria", productosOrdenados);

        return "categorias/detalle";
    }
}