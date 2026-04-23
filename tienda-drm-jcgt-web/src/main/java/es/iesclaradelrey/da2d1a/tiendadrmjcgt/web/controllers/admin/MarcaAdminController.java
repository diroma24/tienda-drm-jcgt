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
            List<String> errores = new ArrayList<>();

            // Validación de Nombre
            if (marca.getNombre() == null || marca.getNombre().isBlank()) {
                errores.add("El nombre de la marca no puede estar vacío.");
            }

            // Validación de Descripción (Opcional pero controlada)
            if (marca.getDescripcion() != null && marca.getDescripcion().length() > 500) {
                errores.add("La descripción es demasiado larga (máximo 500 caracteres).");
            }

            if (!errores.isEmpty()) {
                throw new IllegalArgumentException(String.join("|", errores));
            }

            marcaService.save(marca);
            return "redirect:/admin/marcas";

        } catch (Exception e) {
            model.addAttribute("mensajeError", e.getMessage());
            model.addAttribute("volverUrl", "/admin/marcas/nuevo");
            return "admin/error-validacion";
        }
    }
}