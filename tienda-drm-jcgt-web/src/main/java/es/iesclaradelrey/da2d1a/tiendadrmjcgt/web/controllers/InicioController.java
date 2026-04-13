package es.iesclaradelrey.da2d1a.tiendadrmjcgt.web.controllers;

import org.springframework.stereotype.Controller; // Asegúrate de importar esta
import org.springframework.web.bind.annotation.GetMapping;

@Controller //Controller devuelve una vista (html), restController devuelve datos
public class InicioController {

    @GetMapping("/")
    public String index() {
        return "home"; //Se ha cambiado el nombre del index a home.html para que este sea dinámico
        //Por defecto SpringBoot busca un archivo llamado index.html en static, si está da error
    }
}