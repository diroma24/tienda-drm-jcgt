package es.iesclaradelrey.da2d1a.tiendadrmjcgt.web.controllers.admin;

import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities.Categoria;
import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.services.CategoriaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
            categoriaService.save(categoria);
            return "redirect:/admin/categorias";
        } catch (Exception e) {
            // Requisito 3.3: Captura el mensaje de la excepción y lo envía a la vista
            model.addAttribute("error", e.getMessage());
            // El objeto 'categoria' ya viaja con los datos introducidos gracias a @ModelAttribute
            return "admin/categorias/formulario";
        }
    }
}