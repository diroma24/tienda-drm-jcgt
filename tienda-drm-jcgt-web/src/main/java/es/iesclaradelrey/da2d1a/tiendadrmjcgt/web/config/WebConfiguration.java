package es.iesclaradelrey.da2d1a.tiendadrmjcgt.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Clase de configuración para Spring Web MVC.
 * <p>
 * Implementa {@link WebMvcConfigurer} para permitir la personalización de la configuración
 * predeterminada de Spring MVC. Se utiliza principalmente para definir configuraciones
 * transversales de la capa web, como el mapeo directo de URLs a vistas sin necesidad de
 * crear clases controladoras ({@code @Controller}) explícitas.
 * </p>
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    /**
     * Registra controladores de vista simples automatizados.
     * <p>
     * Este método se utiliza para definir mapeos directos entre una URL solicitada por
     * el cliente y el nombre de una vista (por ejemplo, una plantilla Thymeleaf o un archivo HTML)
     * que debe ser renderizada. En esta configuración, se enlaza la ruta {@code /condiciones}
     * con la vista {@code condiciones}.
     * </p>
     *
     * @param registry el registro ({@link ViewControllerRegistry}) proporcionado por el contexto
     * de Spring donde se configuran y almacenan los mapeos de los controladores de vistas.
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // se mapeamos la URL /condiciones directamente al archivo condiciones.html
        registry.addViewController("/condiciones").setViewName("condiciones");
    }
}