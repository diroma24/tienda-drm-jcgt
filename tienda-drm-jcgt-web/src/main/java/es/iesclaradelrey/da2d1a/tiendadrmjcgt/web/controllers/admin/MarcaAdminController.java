package es.iesclaradelrey.da2d1a.tiendadrmjcgt.web.controllers.admin;

import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities.Marca;
import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.services.MarcaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
            validarMarca(marca);
            marcaService.save(marca);
            return "redirect:/admin/marcas";
        } catch (Exception e) {
            model.addAttribute("mensajeError", e.getMessage());
            model.addAttribute("volverUrl", "/admin/marcas/nuevo");
            return "admin/error-validacion";
        }
    }

    @GetMapping("/{id}/edit")
    public String editar(@PathVariable Long id, Model model) {
        Marca marca = marcaService.findById(id);
        if (marca == null) return "redirect:/admin/marcas";
        model.addAttribute("marca", marca);
        return "admin/marcas/formulario";
    }

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

    private void validarMarca(Marca marca) {
        List<String> errores = new ArrayList<>();
        if (marca.getNombre() == null || marca.getNombre().isBlank()) errores.add("El nombre de la marca es obligatorio.");
        if (!errores.isEmpty()) throw new IllegalArgumentException(String.join("|", errores));
    }
}