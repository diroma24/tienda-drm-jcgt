package es.iesclaradelrey.da2d1a.tiendadrmjcgt.web.controllers;

import org.springframework.stereotype.Controller; // Asegúrate de importar esta
import org.springframework.web.bind.annotation.GetMapping;

@Controller //Controller devuelve una vista (html), restController devuelve datos
public class InicioController {

    @GetMapping("/")
    public String index() {
        return "index";
    }
}