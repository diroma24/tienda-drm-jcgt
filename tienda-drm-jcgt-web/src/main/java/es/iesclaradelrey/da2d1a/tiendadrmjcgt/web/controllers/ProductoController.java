package es.iesclaradelrey.da2d1a.tiendadrmjcgt.web.controllers;

import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities.Producto;
import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.services.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping({"", "/"}) // Atiende /productos y /productos/
    public String listado(Model model) {
        model.addAttribute("productos", productoService.findAll());
        return "productos/listado";
    }

    @GetMapping("/{id}/{slug}")
    public String detalle(@PathVariable("id") Long id, @PathVariable("slug") String slug, Model model) {
        Producto producto = productoService.findById(id);

        // Si el producto no existe, redirigimos al listado (o podrías mostrar una página 404)
        if (producto == null) {
            return "redirect:/productos";
        }

        model.addAttribute("p", producto);
        return "productos/detalle";
    }
}