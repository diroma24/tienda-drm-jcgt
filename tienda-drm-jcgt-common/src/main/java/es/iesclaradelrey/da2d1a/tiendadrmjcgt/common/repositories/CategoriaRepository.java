package es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.repositories;

import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz de acceso a datos para la entidad Categoria.
 * <p>
 * Al extender de JpaRepository, proporciona automáticamente la implementación
 * necesaria para realizar operaciones de persistencia sobre la base de datos,
 * incluyendo métodos de creación, lectura, actualización y borrado.
 * </p>
 */
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    // Espacio reservado para la definición de métodos de consulta derivados (Query Methods)
}