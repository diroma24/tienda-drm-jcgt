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

    /**
     * Recupera la lista completa de productos existentes en el sistema.
     * @return Colección de objetos Producto.
     */
    List<Producto> findAll();

    /**
     * Busca un producto específico mediante su identificador único.
     * @param id Identificador del producto.
     * @return La entidad Producto encontrada o null si no existe.
     */
    Producto findById(Long id);

    /**
     * Gestiona la persistencia de un producto, permitiendo tanto el registro inicial como la actualización.
     * @param producto Objeto con los datos del producto a procesar.
     */
    void save(Producto producto);

    /**
     * Elimina del sistema el producto asociado al identificador proporcionado.
     * @param id Identificador del producto a eliminar.
     */
    void deleteById(Long id);
}