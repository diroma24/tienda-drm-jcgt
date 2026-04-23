package es.iesclaradelrey.da2d1a.tiendadrmjcgt.web.controllers.admin;

import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities.Marca;
import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.services.MarcaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Controlador administrativo para la gestión de marcas.
 * <p>
 * Proporciona las funcionalidades necesarias para el mantenimiento del catálogo de marcas,
 * permitiendo operaciones de creación, lectura, actualización y borrado (CRUD).
 * </p>
 */
@Controller
@RequestMapping("/admin/marcas")
public class MarcaAdminController {

    private final MarcaService marcaService;

    /**
     * Constructor para la inyección del servicio de marcas.
     */
    public MarcaAdminController(MarcaService marcaService) {
        this.marcaService = marcaService;
    }

    /**
     * Muestra el listado de marcas en el panel de administración.
     * @param model Objeto para el paso de datos a la vista.
     * @return Nombre de la plantilla de listado administrativo.
     */
    @GetMapping("")
    public String listar(Model model) {
        model.addAttribute("marcas", marcaService.findAll());
        return "admin/marcas/listado";
    }

    /**
     * Prepara el formulario para el registro de una nueva marca.
     * @param model Objeto para el paso de datos a la vista.
     * @return Nombre de la plantilla del formulario.
     */
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("marca", new Marca());
        return "admin/marcas/formulario";
    }

    /**
     * Procesa el envío del formulario para registrar una nueva marca.
     * Realiza validaciones de integridad antes de persistir la entidad.
     * @param marca Objeto con los datos capturados del formulario.
     * @param model Objeto para la gestión de errores en la vista.
     * @return Redirección al listado o vista de error en caso de fallo.
     */
    @PostMapping("/nuevo")
    public String guardar(@ModelAttribute Marca marca, Model model) {
        try {
            validarMarca(marca);
            marcaService.save(marca);
            return "redirect:/admin/marcas";
        } catch (Exception e) {
            model.addAttribute("mensajeError", e.getMessage());
            model.addAttribute("volverUrl", "/admin/marcas/nuevo");
            return "admin/error-validacion";
        }
    }

    /**
     * Carga los datos de una marca existente para su modificación.
     * @param id Identificador único de la marca.
     * @param model Objeto para el paso de datos a la vista.
     * @return Nombre de la plantilla del formulario o redirección si no existe.
     */
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Marca marca = marcaService.findById(id);
        if (marca == null) return "redirect:/admin/marcas";
        model.addAttribute("marca", marca);
        return "admin/marcas/formulario";
    }

    /**
     * Procesa la actualización de una marca existente.
     * @param id Identificador único proveniente de la ruta.
     * @param marca Objeto con los datos actualizados.
     * @param model Objeto para la gestión de errores.
     * @return Redirección al listado o vista de error.
     */
    @PostMapping("/editar/{id}")
    public String actualizar(@PathVariable Long id, @ModelAttribute Marca marca, Model model) {
        try {
            marca.setId(id);
            validarMarca(marca);
            marcaService.save(marca);
            return "redirect:/admin/marcas";
        } catch (Exception e) {
            model.addAttribute("mensajeError", e.getMessage());
            model.addAttribute("volverUrl", "/admin/marcas/editar/" + id);
            return "admin/error-validacion";
        }
    }

    /**
     * Muestra la pantalla de confirmación para la eliminación de una marca.
     * @param id Identificador de la marca a eliminar.
     * @param model Objeto para el paso de datos a la vista.
     * @return Nombre de la plantilla de confirmación de borrado.
     */
    @GetMapping("/borrar/{id}")
    public String borrarForm(@PathVariable Long id, Model model) {
        Marca marca = marcaService.findById(id);
        if (marca == null) return "redirect:/admin/marcas";
        model.addAttribute("marca", marca);
        return "admin/marcas/borrar";
    }

    /**
     * Ejecuta la eliminación definitiva de una marca.
     * Controla las restricciones de integridad en caso de existir productos asociados.
     * @param id Identificador de la marca enviado por parámetro.
     * @param model Objeto para mostrar mensajes de error en la misma vista.
     * @return Redirección al listado o permanencia en la vista con mensaje de error.
     */
    @PostMapping("/borrar")
    public String eliminar(@RequestParam Long id, Model model) {
        try {
            marcaService.deleteById(id);
            return "redirect:/admin/marcas";
        } catch (Exception e) {
            model.addAttribute("marca", marcaService.findById(id));
            model.addAttribute("error", "No se puede eliminar la marca porque tiene productos asociados: " + e.getMessage());
            return "admin/marcas/borrar";
        }
    }

    /**
     * Realiza comprobaciones de integridad sobre los datos de la marca.
     * @param marca El objeto a validar.
     * @throws IllegalArgumentException si el nombre de la marca es nulo o está vacío.
     */
    private void validarMarca(Marca marca) {
        List<String> errores = new ArrayList<>();
        if (marca.getNombre() == null || marca.getNombre().isBlank()) errores.add("El nombre de la marca es obligatorio.");
        if (!errores.isEmpty()) throw new IllegalArgumentException(String.join("|", errores));
    }
}