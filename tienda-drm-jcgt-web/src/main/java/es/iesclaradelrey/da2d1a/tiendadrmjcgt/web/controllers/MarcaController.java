package es.iesclaradelrey.da2d1a.tiendadrmjcgt.web.controllers;

import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities.Marca;
import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities.Producto;
import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.services.MarcaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/marcas")
public class MarcaController {

    private final MarcaService marcaService;

    public MarcaController(MarcaService marcaService) {
        this.marcaService = marcaService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("listaMarcas", marcaService.findAll());
        return "marcas/listado";
    }

    @GetMapping("/{id}")
    public String detalle(@PathVariable Long id, Model model) {
        Marca marca = marcaService.findById(id);
        if (marca == null) return "redirect:/marcas";

        //Obtiene productos por navegación y ordena con Comparator
        List<Producto> productosOrdenados = new ArrayList<>(marca.getProductos());
        productosOrdenados.sort((p1, p2) -> p1.getNombre().compareToIgnoreCase(p2.getNombre()));

        model.addAttribute("marca", marca);
        model.addAttribute("productosDeLaMarca", productosOrdenados);
        return "marcas/detalle";
    }
}