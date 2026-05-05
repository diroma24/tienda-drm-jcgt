package es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.repositories;

import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities.Categoria;
import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities.Producto;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interfaz de acceso a datos para la entidad Producto.
 * <p>
 * Al extender de JpaRepository, proporciona automáticamente la implementación
 * necesaria para realizar operaciones de persistencia sobre la base de datos,
 * incluyendo métodos de creación, lectura, actualización y borrado.
 * </p>
 */
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByCategoriasId(Long categoryId, Sort sort);
}