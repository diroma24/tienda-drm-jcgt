package es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.repositories;

import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities.Categoria;

/**
 * Interfaz de repositorio especializada para la gestión de entidades {@link Categoria}.
 * <p>
 * Hereda todas las operaciones estándar de {@link Repository} y actúa como el
 * contrato oficial para el acceso a datos relacionados con las categorías del sistema.
 * </p>
 * * @see Categoria
 * @see Repository
 */
public interface CategoriaRepository extends Repository<Categoria, Long> {
    // Métodos específicos
}