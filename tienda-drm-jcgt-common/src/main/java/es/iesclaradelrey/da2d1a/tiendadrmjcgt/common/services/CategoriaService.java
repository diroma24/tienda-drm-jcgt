package es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.services;

import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities.Categoria;
import java.util.Collection;
import java.util.Optional;

/**
 * Interfaz que define los servicios de negocio para la gestión de categorías.
 * <p>
 * Actúa como capa intermedia entre el controlador y el repositorio,
 * encargándose de orquestar las reglas de negocio aplicables a las categorías.
 * </p>
 */
public interface CategoriaService {
    void add(Categoria categoria);
    Collection<Categoria> getAll();
    Optional<Categoria> getById(Long id);
    void update(Categoria categoria);
    void delete(Long id);
}
