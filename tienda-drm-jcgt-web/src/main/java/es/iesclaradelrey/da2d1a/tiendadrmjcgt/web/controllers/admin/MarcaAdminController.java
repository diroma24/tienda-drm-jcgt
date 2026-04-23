package es.iesclaradelrey.da2d1a.tiendadrmjcgt.web.controllers.admin;

import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities.Marca;
import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.services.MarcaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/marcas")
public class MarcaAdminController {

    private final MarcaService marcaService;

    public MarcaAdminController(MarcaService marcaService) {
        this.marcaService = marcaService;
    }

    @GetMapping("")
    public String listar(Model model) {
        model.addAttribute("marcas", marcaService.findAll());
        return "admin/marcas/listado";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("marca", new Marca());
        return "admin/marcas/formulario";
    }

    @PostMapping("/nuevo")
    public String guardar(@ModelAttribute Marca marca, Model model) {
        try {
            marcaService.save(marca);
            return "redirect:/admin/marcas";
        } catch (Exception e) {
            model.addAttribute("error", "No se pudo crear la marca: " + e.getMessage());
            // El objeto 'marca' se mantiene automáticamente para no perder lo escrito
            return "admin/marcas/formulario";
        }
    }
}