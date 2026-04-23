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

import java.util.ArrayList;
import java.util.List;

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
            List<String> errores = new ArrayList<>();

            // Validación de Atributos Básicos
            if (producto.getNombre() == null || producto.getNombre().isBlank()) errores.add("El nombre es obligatorio.");
            if (producto.getPrecio() == null || producto.getPrecio() < 0) errores.add("El precio no puede ser negativo.");

            // VALIDACIÓN DE RELACIONES (REQUISITO 3.3)
            if (producto.getMarca() == null || producto.getMarca().getId() == null) {
                errores.add("Debe seleccionar obligatoriamente una Marca para el producto.");
            }

            // Las categorías suelen ser un Set o List. Si es obligatorio tener al menos una:
            if (producto.getCategorias() == null || producto.getCategorias().isEmpty()) {
                errores.add("El producto debe pertenecer al menos a una categoría.");
            }

            if (!errores.isEmpty()) {
                throw new IllegalArgumentException(String.join("|", errores));
            }

            productoService.save(producto);
            return "redirect:/admin/productos";

        } catch (Exception e) {
            model.addAttribute("mensajeError", e.getMessage());
            model.addAttribute("volverUrl", "/admin/productos/nuevo");
            return "admin/error-validacion";
        }
    }
}