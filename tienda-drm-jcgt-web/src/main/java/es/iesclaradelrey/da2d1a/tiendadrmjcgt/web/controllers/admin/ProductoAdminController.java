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

/**
 * Controlador administrativo para la gestión integral de productos.
 * <p>
 * Coordina las operaciones de mantenimiento del catálogo de productos, integrando
 * la gestión de marcas y categorías necesarias para su correcta clasificación.
 * </p>
 */
@Controller
@RequestMapping("/admin/productos")
public class ProductoAdminController {

    private final ProductoService productoService;
    private final MarcaService marcaService;
    private final CategoriaService categoriaService;

    /**
     * Constructor para la inyección de dependencias de los servicios de productos, marcas y categorías.
     */
    public ProductoAdminController(ProductoService productoService, MarcaService marcaService, CategoriaService categoriaService) {
        this.productoService = productoService;
        this.marcaService = marcaService;
        this.categoriaService = categoriaService;
    }

    /**
     * Recupera y muestra el listado global de productos en la zona de administración.
     * @param model Objeto para el paso de datos a la vista.
     * @return Nombre de la plantilla del listado administrativo.
     */
    @GetMapping("")
    public String listar(Model model) {
        model.addAttribute("productos", productoService.findAll());
        return "admin/productos/listado";
    }

    /**
     * Prepara el formulario de alta para un nuevo producto.
     * Inicializa los desplegables de marcas y categorías.
     * @param model Objeto para el paso de datos a la vista.
     * @return Nombre de la plantilla del formulario.
     */
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("producto", new Producto());
        cargarCombos(model);
        return "admin/productos/formulario";
    }

    /**
     * Procesa el registro de un nuevo producto tras validar los datos recibidos.
     * @param producto Objeto con los datos del formulario.
     * @param model Objeto para la gestión de errores.
     * @return Redirección al listado o vista de error de validación.
     */
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

    /**
     * Carga los datos de un producto para su edición.
     * @param id Identificador único del producto.
     * @param model Objeto para el paso de datos a la vista.
     * @return Plantilla del formulario o redirección si el producto no existe.
     */
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Producto producto = productoService.findById(id);
        if (producto == null) return "redirect:/admin/productos";
        model.addAttribute("producto", producto);
        cargarCombos(model);
        return "admin/productos/formulario";
    }

    /**
     * Procesa la actualización de los datos de un producto existente.
     * @param id Identificador del producto a modificar.
     * @param producto Objeto con la información actualizada.
     * @param model Objeto para gestionar excepciones de validación.
     * @return Redirección al listado o vista de error.
     */
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

    /**
     * Muestra la interfaz de confirmación para eliminar un producto.
     * @param id Identificador del producto a borrar.
     * @param model Objeto para el paso de datos a la vista.
     * @return Plantilla de confirmación de borrado.
     */
    @GetMapping("/borrar/{id}")
    public String borrarForm(@PathVariable Long id, Model model) {
        Producto producto = productoService.findById(id);
        if (producto == null) return "redirect:/admin/productos";
        model.addAttribute("producto", producto);
        return "admin/productos/borrar";
    }

    /**
     * Ejecuta la eliminación física del producto seleccionado.
     * @param id Identificador enviado por parámetro de formulario.
     * @param model Objeto para capturar errores de integridad.
     * @return Redirección al listado o permanencia en vista de borrado con mensaje de error.
     */
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

    /**
     * Carga en el modelo las colecciones necesarias para los elementos de selección del formulario.
     */
    private void cargarCombos(Model model) {
        model.addAttribute("marcas", marcaService.findAll());
        model.addAttribute("categorias", categoriaService.findAll());
    }

    /**
     * Ejecuta la lógica de validación de negocio para la entidad Producto.
     * @param producto Instancia del producto a validar.
     * @throws IllegalArgumentException si los datos no cumplen con los requisitos mínimos.
     */
    private void validarProducto(Producto producto) {
        List<String> errores = new ArrayList<>();
        if (producto.getNombre() == null || producto.getNombre().isBlank()) errores.add("Nombre obligatorio");
        if (producto.getPrecio() == null || producto.getPrecio() < 0) errores.add("Precio inválido");
        if (producto.getMarca() == null || producto.getMarca().getId() == null) errores.add("Seleccione marca");
        if (producto.getCategorias() == null || producto.getCategorias().isEmpty()) errores.add("Seleccione al menos una categoría");
        if (!errores.isEmpty()) throw new IllegalArgumentException(String.join("|", errores));
    }
}