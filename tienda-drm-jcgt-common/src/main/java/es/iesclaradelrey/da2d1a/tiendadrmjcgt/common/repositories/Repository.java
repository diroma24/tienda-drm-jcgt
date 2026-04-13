package es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.repositories;

import java.util.Collection;
import java.util.Optional;

/**
 * Interfaz genérica que define las operaciones por defecto de un repositorio (CRUD).
 * <p>
 * Proporciona una abstracción para el acceso a datos, permitiendo gestionar
 * entidades de cualquier tipo de forma estandarizada.
 * </p>
 *
 * @param <T>  El tipo de la entidad que gestiona el repositorio.
 * @param <ID> El tipo del identificador único de la entidad.
 */
public interface Repository<T, ID> {
    void add(T entity);
    Collection<T> getAll();
    Optional<T> getById(ID id);
    void update(T entity);
    void delete(ID id);
}