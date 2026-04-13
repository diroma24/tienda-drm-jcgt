package es.iesclaradelrey.da2d1a.tiendadrmjcgt.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "es.iesclaradelrey.da2d1a.tiendadrmjcgt.web",
        "es.iesclaradelrey.da2d1a.tiendadrmjcgt.common"
})
public class WebApp {

    public static void main(String[] args) {
        SpringApplication.run(WebApp.class, args);
    }

}
