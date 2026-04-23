package es.iesclaradelrey.da2d1a.tiendadrmjcgt.web.controllers.admin;

import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities.Producto;
import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.services.ProductoService;
import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.services.MarcaService;
import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.services.CategoriaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("")
    public String listar(Model model) {
        model.addAttribute("productos", productoService.findAll());
        return "admin/productos/listado";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("producto", new Producto());
        cargarCombos(model);
        return "admin/productos/formulario";
    }

    @PostMapping("/nuevo")
    public String guardar(@ModelAttribute Producto producto, Model model) {
        try {
            validarProducto(producto);
            productoService.save(producto);
            return "redirect:/admin/productos";
        } catch (Exception e) {
            model.addAttribute("mensajeError", e.getMessage());
            model.addAttribute("volverUrl", "/admin/productos/nuevo");
            return "admin/error-validacion";
        }
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Producto producto = productoService.findById(id);
        if (producto == null) return "redirect:/admin/productos";
        model.addAttribute("producto", producto);
        cargarCombos(model);
        return "admin/productos/formulario";
    }

    @PostMapping("/editar/{id}")
    public String actualizar(@PathVariable Long id, @ModelAttribute Producto producto, Model model) {
        try {
            producto.setId(id);
            validarProducto(producto);
            productoService.save(producto);
            return "redirect:/admin/productos";
        } catch (Exception e) {
            model.addAttribute("mensajeError", e.getMessage());
            model.addAttribute("volverUrl", "/admin/productos/editar/" + id);
            return "admin/error-validacion";
        }
    }

    @GetMapping("/borrar/{id}")
    public String borrarForm(@PathVariable Long id, Model model) {
        Producto producto = productoService.findById(id);
        if (producto == null) return "redirect:/admin/productos";
        model.addAttribute("producto", producto);
        return "admin/productos/borrar";
    }

    @PostMapping("/borrar")
    public String eliminar(@RequestParam Long id, Model model) {
        try {
            productoService.deleteById(id);
            return "redirect:/admin/productos";
        } catch (Exception e) {
            model.addAttribute("producto", productoService.findById(id));
            model.addAttribute("error", "Error: " + e.getMessage());
            return "admin/productos/borrar";
        }
    }

    private void cargarCombos(Model model) {
        model.addAttribute("marcas", marcaService.findAll());
        model.addAttribute("categorias", categoriaService.findAll());
    }

    private void validarProducto(Producto producto) {
        List<String> errores = new ArrayList<>();
        if (producto.getNombre() == null || producto.getNombre().isBlank()) errores.add("Nombre obligatorio");
        if (producto.getPrecio() == null || producto.getPrecio() < 0) errores.add("Precio inválido");
        if (producto.getMarca() == null || producto.getMarca().getId() == null) errores.add("Seleccione marca");
        if (producto.getCategorias() == null || producto.getCategorias().isEmpty()) errores.add("Seleccione al menos una categoría");
        if (!errores.isEmpty()) throw new IllegalArgumentException(String.join("|", errores));
    }
}