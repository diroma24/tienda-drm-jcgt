package es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.repositories;

import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities.EventoSeguridad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio de acceso a datos para la entidad EventoSeguridad.
 * Proporciona métodos estándar de CRUD para la gestión y consulta de los registros de auditoría de seguridad.
 */
@Repository
public interface EventoSeguridadRepository extends JpaRepository<EventoSeguridad, Long> {
}