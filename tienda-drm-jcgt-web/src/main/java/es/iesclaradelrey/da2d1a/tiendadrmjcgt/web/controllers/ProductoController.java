package es.iesclaradelrey.da2d1a.tiendadrmjcgt.web.controllers;

import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.services.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }


}