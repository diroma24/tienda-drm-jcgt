package es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.repositories;

import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz de repositorio especializada para la gestión de entidades {@link Categoria} mediante JPA.
 * Hereda operaciones CRUD estándar (save, findAll, findById, etc.) de {@link JpaRepository}.
 */
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    // Métodos específicos
}