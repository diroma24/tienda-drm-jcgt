package es.iesclaradelrey.da2d1a.tiendadrmjcgt.web.util;

import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities.Categoria;
import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.services.CategoriaService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Componente encargado de la precarga de datos al iniciar la aplicación.
 * <p>
 * Al implementar {@link CommandLineRunner}, el método {@code run} se ejecuta
 * automáticamente una vez que el contexto de Spring se ha cargado completamente.
 * Se utiliza principalmente para configurar el estado inicial de la base de datos
 * o el almacenamiento en memoria.
 * </p>
 */
@Component
public class DataInitializer implements CommandLineRunner {

    private final CategoriaService categoriaService;

    // Se inyecta el servicio de categorías por constructor
    public DataInitializer(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @Override
    public void run(String... args) throws Exception {
        // 1. Categoría con imagen (Sobres)
        categoriaService.add(new Categoria(1L, "Sobres",
                "Ampliaciones de las últimas ediciones como Escarlata y Púrpura.", "sobres.jpg"));

        // 2. Categoría con imagen (Mazos)
        categoriaService.add(new Categoria(2L, "Mazos",
                "Mazos listos para jugar, ideales para entrenadores principiantes.", null));

        // 3. Categoría con imagen (Accesorios)
        categoriaService.add(new Categoria(3L, "Accesorios",
                "Protege tus cartas con las mejores fundas y cajas de almacenamiento.", "accesorios.jpg"));

        // 4. Categoría SIN imagen (Cartas Sueltas)
        categoriaService.add(new Categoria(4L, "Cartas",
                "Cartas raras, Full Art y Secretas vendidas por separado.", "cartas.jpg"));

        System.out.println("Categorías de Pokémon TCG cargadas con éxito.");
    }
}
