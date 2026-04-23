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

/**
 * Controlador para la gestión de la navegación pública por marcas.
 * <p>
 * Facilita la visualización de los fabricantes disponibles y permite
 * acceder al catálogo de productos asociado a cada uno de ellos.
 * </p>
 */
@Controller // Especializa la clase como un componente de presentación de Spring MVC
@RequestMapping("/marcas") // Define el prefijo de ruta para todas las peticiones del controlador
public class MarcaController {

    private final MarcaService marcaService;

    /**
     * Constructor para la inyección del servicio de marcas.
     */
    public MarcaController(MarcaService marcaService) {
        this.marcaService = marcaService;
    }

    /**
     * Recupera y muestra la lista completa de marcas comerciales.
     * Envía la colección al modelo para su representación en la vista de listado.
     * * @param model Objeto para el intercambio de datos con la vista.
     * @return Nombre de la plantilla del listado de marcas.
     */
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("listaMarcas", marcaService.findAll());
        return "marcas/listado";
    }

    /**
     * Muestra la información detallada de una marca y sus productos vinculados.
     * Realiza una ordenación alfabética de los productos antes de enviarlos a la vista.
     * * @param id Identificador único de la marca.
     * @param model Objeto para el intercambio de datos con la vista.
     * @return Nombre de la plantilla de detalle o redirección si la marca no existe.
     */
    @GetMapping("/{id}")
    public String detalle(@PathVariable Long id, Model model) {
        Marca marca = marcaService.findById(id);
        if (marca == null) return "redirect:/marcas";

        // Genera una lista mutable de los productos para aplicar ordenación nominal
        List<Producto> productosOrdenados = new ArrayList<>(marca.getProductos());
        productosOrdenados.sort((p1, p2) -> p1.getNombre().compareToIgnoreCase(p2.getNombre()));

        model.addAttribute("marca", marca);
        model.addAttribute("productosDeLaMarca", productosOrdenados);
        return "marcas/detalle";
    }
}