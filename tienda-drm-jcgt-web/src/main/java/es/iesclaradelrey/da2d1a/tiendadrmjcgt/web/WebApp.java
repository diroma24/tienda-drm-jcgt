package es.iesclaradelrey.da2d1a.tiendadrmjcgt.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan; // Import corregido
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
        "es.iesclaradelrey.da2d1a.tiendadrmjcgt.web",
        "es.iesclaradelrey.da2d1a.tiendadrmjcgt.common",
        "es.iesclaradelrey.da2d1a.tiendadrmjcgt.security"
})
@EnableJpaRepositories(basePackages = "es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.repositories")
@EntityScan(basePackages = "es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities")
public class WebApp {

    public static void main(String[] args) {
        SpringApplication.run(WebApp.class, args);
    }
}