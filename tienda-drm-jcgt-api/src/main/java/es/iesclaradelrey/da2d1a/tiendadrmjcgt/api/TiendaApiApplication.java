package es.iesclaradelrey.da2d1a.tiendadrmjcgt.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {
        "es.iesclaradelrey.da2d1a.tiendadrmjcgt.api",
        "es.iesclaradelrey.da2d1a.tiendadrmjcgt.common",
        "es.iesclaradelrey.da2d1a.tiendadrmjcgt.security"
})
@EntityScan(basePackages = "es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities")
@EnableJpaRepositories(basePackages = "es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.repositories")
public class TiendaApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TiendaApiApplication.class, args);
    }
}