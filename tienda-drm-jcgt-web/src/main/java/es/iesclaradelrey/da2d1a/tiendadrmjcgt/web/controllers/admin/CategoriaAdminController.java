package es.iesclaradelrey.da2d1a.tiendadrmjcgt.web.controllers.admin;

import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities.Categoria;
import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.services.CategoriaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
            validarCategoria(categoria);
            categoriaService.save(categoria);
            return "redirect:/admin/categorias";
        } catch (Exception e) {
            model.addAttribute("mensajeError", e.getMessage());
            model.addAttribute("volverUrl", "/admin/categorias/nuevo");
            return "admin/error-validacion";
        }
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Categoria categoria = categoriaService.findById(id);
        if (categoria == null) return "redirect:/admin/categorias";
        model.addAttribute("categoria", categoria);
        return "admin/categorias/formulario";
    }

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

    // --- REQUISITO 3.5: BORRADO ---
    @GetMapping("/borrar/{id}")
    public String borrarForm(@PathVariable Long id, Model model) {
        Categoria categoria = categoriaService.findById(id);
        if (categoria == null) return "redirect:/admin/categorias";
        model.addAttribute("categoria", categoria);
        return "admin/categorias/borrar";
    }

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

    private void validarCategoria(Categoria categoria) {
        List<String> errores = new ArrayList<>();
        if (categoria.getNombre() == null || categoria.getNombre().isBlank()) errores.add("El nombre es obligatorio.");
        if (categoria.getDescripcion() == null || categoria.getDescripcion().isBlank()) errores.add("La descripción es obligatoria.");
        if (!errores.isEmpty()) throw new IllegalArgumentException(String.join("|", errores));
    }
}