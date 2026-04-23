package es.iesclaradelrey.da2d1a.tiendadrmjcgt.web.controllers.admin;

import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities.Producto;
import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.services.ProductoService;

import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities.Marca;
import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.services.MarcaService;

import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities.Categoria;
import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.services.CategoriaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/productos")
public class ProductoAdminController {

    private final ProductoService productoService;
    private final MarcaService marcaService;
    private final CategoriaService categoriaService;

    public ProductoAdminController(ProductoService productoService, MarcaService marcaService, CategoriaService categoriaService) {
        this.productoService = productoService;
        this.marcaService = marcaService;
        this.categoriaService = categoriaService;
    }

    // PUNTO 3.2: Listado principal de administración
    @GetMapping("")
    public String listar(Model model) {
        model.addAttribute("productos", productoService.findAll());
        return "admin/productos/listado";
    }

    // PUNTO 3.3: Mostrar formulario de creación
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("producto", new Producto());
        // Usamos findAll() que es el nombre estándar en tus servicios
        model.addAttribute("marcas", marcaService.findAll());
        model.addAttribute("categorias", categoriaService.findAll());
        return "admin/productos/formulario";
    }

    // PUNTO 3.3: Procesar el guardado
    @PostMapping("/nuevo")
    public String guardar(@ModelAttribute Producto producto, Model model) {
        try {
            // Asegúrate de que tu service tenga el método save o add
            productoService.save(producto);
            return "redirect:/admin/productos";
        } catch (Exception e) {
            // Capturamos el error para mostrarlo en el formulario sin perder datos
            model.addAttribute("error", "Error al guardar: " + e.getMessage());
            model.addAttribute("marcas", marcaService.findAll());
            model.addAttribute("categorias", categoriaService.findAll());
            return "admin/productos/formulario";
        }
    }
}