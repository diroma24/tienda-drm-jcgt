package es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.services;

import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities.Marca;
import java.util.List;

/**
 * Interfaz que define los servicios de negocio para la gestión de marcas.
 * <p>
 * Actúa como capa intermedia entre el controlador y el repositorio,
 * encargándose de orquestar las reglas de negocio aplicables a las marcas.
 * </p>
 */
public interface MarcaService {
    List<Marca> findAll();
    Marca findById(Long id);
}