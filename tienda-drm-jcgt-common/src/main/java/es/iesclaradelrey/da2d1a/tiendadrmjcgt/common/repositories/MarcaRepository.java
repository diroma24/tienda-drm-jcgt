package es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.repositories;

import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities.Categoria;
import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfaz de acceso a datos para la entidad Marca.
 * <p>
 * Al extender de JpaRepository, proporciona automáticamente la implementación
 * necesaria para realizar operaciones de persistencia sobre la base de datos,
 * incluyendo métodos de creación, lectura, actualización y borrado.
 * </p>
 */
@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long> {
    // Espacio reservado para la definición de métodos de consulta derivados (Query Methods)
}