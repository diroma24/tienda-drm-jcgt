package es.iesclaradelrey.da2d1a.tiendadrmjcgt.web.controllers;

import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities.Producto;
import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.services.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controlador encargado de la exposición pública de los productos.
 * <p>
 * Gestiona las peticiones relativas a la visualización del catálogo general
 * y la ficha detallada de cada artículo.
 * </p>
 */
@Controller // Define la clase como controlador para el manejo de peticiones web
@RequestMapping("/productos") // Establece el prefijo de ruta base para los productos
public class ProductoController {

    private final ProductoService productoService;

    /**
     * Constructor para la inyección del servicio de productos.
     */
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    /**
     * Recupera y muestra la lista completa de productos disponibles en la tienda.
     * Envía la colección de productos al modelo para su renderizado.
     * * @param model Objeto para el paso de información a la vista.
     * @return Nombre de la plantilla para el listado de productos.
     */
    @GetMapping({"", "/"})
    public String listado(Model model) {
        model.addAttribute("productos", productoService.findAll());
        return "productos/listado";
    }

    /**
     * Muestra la información detallada de un producto específico.
     * Requiere tanto el identificador como el slug semántico para la resolución de la URL.
     * * @param id Identificador único del producto.
     * @param slug Cadena de texto amigable para SEO (no utilizada en la búsqueda).
     * @param model Objeto para el paso de información a la vista.
     * @return Nombre de la plantilla de detalle o redirección si el producto no existe.
     */
    @GetMapping("/{id}/{slug}")
    public String detalle(@PathVariable("id") Long id, @PathVariable("slug") String slug, Model model) {
        Producto producto = productoService.findById(id);

        if (producto == null) {
            return "redirect:/productos";
        }

        model.addAttribute("p", producto);
        return "productos/detalle";
    }
}