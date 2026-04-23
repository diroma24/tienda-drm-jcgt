package es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.services;

import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities.Categoria;
import java.util.List;

/**
 * Interfaz que define los servicios de negocio para la gestión de categorías.
 * <p>
 * Actúa como capa intermedia entre el controlador y el repositorio,
 * encargándose de orquestar las reglas de negocio aplicables a las categorías.
 * </p>
 */
public interface CategoriaService {

    /**
     * Recupera la lista completa de categorías existentes en el sistema.
     */
    List<Categoria> findAll();

    /**
     * Busca una categoría específica mediante su identificador único.
     */
    Categoria findById(Long id);

    /**
     * Gestiona la persistencia de una categoría, permitiendo tanto el registro inicial como la actualización.
     */
    void save(Categoria categoria);

    /**
     * Elimina del sistema la categoría asociada al identificador proporcionado.
     */
    void deleteById(Long id);
}