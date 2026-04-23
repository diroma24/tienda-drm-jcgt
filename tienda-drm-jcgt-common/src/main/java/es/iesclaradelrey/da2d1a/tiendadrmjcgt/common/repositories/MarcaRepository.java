package es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.repositories;

import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities.Categoria;
import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfaz de repositorio especializada para la gestión de entidades {@link Marca} mediante JPA.
 * Hereda operaciones CRUD estándar (save, findAll, findById, etc.) de {@link JpaRepository}.
 */
@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long> {
}