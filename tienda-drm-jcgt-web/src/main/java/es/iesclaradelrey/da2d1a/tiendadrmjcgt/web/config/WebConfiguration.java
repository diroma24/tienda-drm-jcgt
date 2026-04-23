package es.iesclaradelrey.da2d1a.tiendadrmjcgt.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Clase de configuración para Spring Web MVC.
 * <p>
 * Implementa {@link WebMvcConfigurer} para personalizar la configuración
 * de la capa web. Permite definir mapeos directos de URLs a vistas sin
 * requerir controladores explícitos para contenido estático o simple.
 * </p>
 */
@Configuration // Indica que la clase contiene definiciones de beans y configuración de Spring
public class WebConfiguration implements WebMvcConfigurer {

    /**
     * Registra controladores de vista simplificados en el sistema.
     * <p>
     * Establece una relación directa entre rutas URL y nombres de plantillas,
     * optimizando la gestión de páginas que no requieren lógica de negocio previa.
     * </p>
     *
     * @param registry Registro de controladores de vista para añadir los mapeos.
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // Enlaza la ruta de navegación con el nombre físico de la plantilla correspondiente
        registry.addViewController("/condiciones").setViewName("condiciones");
    }
}