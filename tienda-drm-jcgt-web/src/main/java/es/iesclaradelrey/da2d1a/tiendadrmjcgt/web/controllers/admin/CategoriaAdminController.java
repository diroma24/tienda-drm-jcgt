package es.iesclaradelrey.da2d1a.tiendadrmjcgt.web.controllers.admin;

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
@RequestMapping("/admin/categorias")
public class CategoriaAdminController {

    private final CategoriaService categoriaService;

    public CategoriaAdminController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping("")
    public String listar(Model model) {
        model.addAttribute("categorias", categoriaService.findAll());
        return "admin/categorias/listado";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "admin/categorias/formulario";
    }

    @PostMapping("/nuevo")
    public String guardar(@ModelAttribute Categoria categoria, Model model) {
        try {
            List<String> errores = new ArrayList<>();

            // Validación de Nombre
            if (categoria.getNombre() == null || categoria.getNombre().isBlank()) {
                errores.add("El nombre de la categoría es obligatorio.");
            } else if (categoria.getNombre().length() < 3) {
                errores.add("El nombre de la categoría es demasiado corto (mínimo 3 caracteres).");
            }

            // Validación de Descripción (Obligatoria según tu petición)
            if (categoria.getDescripcion() == null || categoria.getDescripcion().isBlank()) {
                errores.add("La descripción de la categoría es obligatoria para informar al cliente.");
            } else if (categoria.getDescripcion().length() > 1000) {
                errores.add("La descripción no puede superar los 1000 caracteres.");
            }

            // Si hay fallos, disparamos la página de error dedicada
            if (!errores.isEmpty()) {
                throw new IllegalArgumentException(String.join("|", errores));
            }

            categoriaService.save(categoria);
            return "redirect:/admin/categorias";

        } catch (Exception e) {
            model.addAttribute("mensajeError", e.getMessage());
            model.addAttribute("volverUrl", "/admin/categorias/nuevo");
            return "admin/error-validacion";
        }
    }
}