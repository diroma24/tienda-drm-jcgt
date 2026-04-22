package es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.services;

import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities.Producto;
import java.util.List;

/**
 * Interfaz que define los servicios de negocio para la gestión de productos.
 * <p>
 * Actúa como capa intermedia entre el controlador y el repositorio,
 * encargándose de orquestar las reglas de negocio aplicables a los productos.
 * </p>
 */
public interface ProductoService {
    List<Producto> findAll();
    Producto findById(Long id);
}