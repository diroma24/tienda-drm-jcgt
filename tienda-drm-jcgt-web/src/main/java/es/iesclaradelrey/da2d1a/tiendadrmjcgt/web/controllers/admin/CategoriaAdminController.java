package es.iesclaradelrey.da2d1a.tiendadrmjcgt.web.controllers.admin;

import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities.Categoria;
import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.services.CategoriaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Controlador administrativo para la gestión de categorías.
 * <p>
 * Proporciona las funcionalidades necesarias para el mantenimiento del catálogo de categorías,
 * permitiendo operaciones de creación, lectura, actualización y borrado (CRUD).
 * </p>
 */
@Controller
@RequestMapping("/admin/categorias")
public class CategoriaAdminController {

    private final CategoriaService categoriaService;

    /**
     * Constructor para la inyección del servicio de categorías.
     */
    public CategoriaAdminController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    /**
     * Muestra el listado de categorías en el panel de administración.
     * @param model Objeto para el paso de datos a la vista.
     * @return Nombre de la plantilla de listado administrativo.
     */
    @GetMapping("")
    public String listar(Model model) {
        model.addAttribute("categorias", categoriaService.findAll());
        return "admin/categorias/listado";
    }

    /**
     * Prepara el formulario para la creación de una nueva categoría.
     * @param model Objeto para el paso de datos a la vista.
     * @return Nombre de la plantilla del formulario.
     */
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "admin/categorias/formulario";
    }

    /**
     * Procesa el envío del formulario para registrar una nueva categoría.
     * Realiza validaciones previas antes de persistir los datos.
     * @param categoria Objeto con los datos capturados del formulario.
     * @param model Objeto para la gestión de errores en la vista.
     * @return Redirección al listado o vista de error en caso de fallo.
     */
    @PostMapping("/nuevo")
    public String guardar(@ModelAttribute Categoria categoria, Model model) {
        try {
            validarCategoria(categoria);
            categoriaService.save(categoria);
            return "redirect:/admin/categorias";
        } catch (Exception e) {
            model.addAttribute("mensajeError", e.getMessage());
            model.addAttribute("volverUrl", "/admin/categorias/nuevo");
            return "admin/error-validacion";
        }
    }

    /**
     * Carga los datos de una categoría existente para su edición.
     * @param id Identificador único de la categoría.
     * @param model Objeto para el paso de datos a la vista.
     * @return Nombre de la plantilla del formulario o redirección si no existe.
     */
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Categoria categoria = categoriaService.findById(id);
        if (categoria == null) return "redirect:/admin/categorias";
        model.addAttribute("categoria", categoria);
        return "admin/categorias/formulario";
    }

    /**
     * Procesa la actualización de una categoría existente.
     * @param id Identificador único proveniente de la ruta.
     * @param categoria Objeto con los datos actualizados.
     * @param model Objeto para la gestión de errores.
     * @return Redirección al listado o vista de error.
     */
    @PostMapping("/editar/{id}")
    public String actualizar(@PathVariable Long id, @ModelAttribute Categoria categoria, Model model) {
        try {
            categoria.setId(id);
            validarCategoria(categoria);
            categoriaService.save(categoria);
            return "redirect:/admin/categorias";
        } catch (Exception e) {
            model.addAttribute("mensajeError", e.getMessage());
            model.addAttribute("volverUrl", "/admin/categorias/editar/" + id);
            return "admin/error-validacion";
        }
    }

    /**
     * Muestra la pantalla de confirmación para el borrado de una categoría.
     * @param id Identificador de la categoría a eliminar.
     * @param model Objeto para el paso de datos a la vista.
     * @return Nombre de la plantilla de confirmación de borrado.
     */
    @GetMapping("/borrar/{id}")
    public String borrarForm(@PathVariable Long id, Model model) {
        Categoria categoria = categoriaService.findById(id);
        if (categoria == null) return "redirect:/admin/categorias";
        model.addAttribute("categoria", categoria);
        return "admin/categorias/borrar";
    }

    /**
     * Ejecuta la eliminación definitiva de una categoría.
     * Gestiona excepciones en caso de que existan restricciones de integridad.
     * @param id Identificador de la categoría enviado por parámetro.
     * @param model Objeto para mostrar mensajes de error en la misma vista.
     * @return Redirección al listado o permanencia en la vista con mensaje de error.
     */
    @PostMapping("/borrar")
    public String eliminar(@RequestParam Long id, Model model) {
        try {
            categoriaService.deleteById(id);
            return "redirect:/admin/categorias";
        } catch (Exception e) {
            model.addAttribute("categoria", categoriaService.findById(id));
            model.addAttribute("error", "No se puede eliminar: " + e.getMessage());
            return "admin/categorias/borrar";
        }
    }

    /**
     * Realiza comprobaciones de integridad sobre los datos de la categoría.
     * @param categoria El objeto a validar.
     * @throws IllegalArgumentException si algún campo obligatorio está ausente o vacío.
     */
    private void validarCategoria(Categoria categoria) {
        List<String> errores = new ArrayList<>();
        if (categoria.getNombre() == null || categoria.getNombre().isBlank()) errores.add("El nombre es obligatorio.");
        if (categoria.getDescripcion() == null || categoria.getDescripcion().isBlank()) errores.add("La descripción es obligatoria.");
        if (!errores.isEmpty()) throw new IllegalArgumentException(String.join("|", errores));
    }
}