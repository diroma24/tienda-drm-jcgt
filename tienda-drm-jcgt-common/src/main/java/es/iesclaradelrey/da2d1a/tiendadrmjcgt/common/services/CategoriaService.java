package es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.services;

import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities.Categoria;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz que define los servicios de negocio para la gestión de categorías.
 * <p>
 * Actúa como capa intermedia entre el controlador y el repositorio,
 * encargándose de orquestar las reglas de negocio aplicables a las categorías.
 * </p>
 */
public interface CategoriaService {
    List<Categoria> findAll(); // Estandarizamos a findAll
    Categoria findById(Long id);
    void save(Categoria categoria); // save sirve para add y update
    void deleteById(Long id);
}
