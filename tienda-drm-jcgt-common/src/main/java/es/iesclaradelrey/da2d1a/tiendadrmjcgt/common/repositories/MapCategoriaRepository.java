package es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.repositories;

import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities.Categoria;
import org.springframework.stereotype.Repository;

/**
 * Implementación persistente en memoria para la gestión de categorías.
 * <p>
 * Esta clase extiende {@link MapRepository} y concreta el contrato de
 * {@link CategoriaRepository}, utilizando un {@code HashMap} para almacenar
 * los objetos {@link Categoria}.
 * </p>
 */
@Repository
public class MapCategoriaRepository extends MapRepository<Categoria, Long> implements CategoriaRepository {

    /**
     * Registra una nueva categoría en el almacenamiento.
     * <p>
     * Se utiliza el identificador de la entidad {@link Categoria#getId()}
     * como clave primaria dentro del mapa de datos.
     * </p>
     *
     * @param categoria El objeto categoría a guardar. Debe tener un ID válido asignado.
     */
    @Override
    public void add(Categoria categoria) {
        // La categoría se guarda con su ID como llave del Map
        data.put(categoria.getId(), categoria);
    }

    /**
     * Actualiza una categoría existente si su identificador ya se encuentra registrado.
     * <p>
     * Este método realiza una verificación previa mediante {@code containsKey}
     * para evitar inserciones accidentales de categorías que no existían previamente.
     * </p>
     *
     * @param categoria La categoría con los datos actualizados.
     */
    @Override
    public void update(Categoria categoria) {
        if (data.containsKey(categoria.getId())) {
            data.put(categoria.getId(), categoria);
        }
    }
}