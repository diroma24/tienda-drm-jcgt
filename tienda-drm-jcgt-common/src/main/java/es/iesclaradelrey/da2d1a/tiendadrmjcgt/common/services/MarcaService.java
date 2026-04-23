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

    /**
     * Recupera la lista completa de marcas existentes en el sistema.
     * @return Colección de objetos Marca.
     */
    List<Marca> findAll();

    /**
     * Busca una marca específica mediante su identificador único.
     * @param id Identificador de la marca.
     * @return La entidad Marca encontrada o null si no existe.
     */
    Marca findById(Long id);

    /**
     * Gestiona la persistencia de una marca, permitiendo tanto el registro inicial como la actualización.
     * @param marca Objeto con los datos de la marca a procesar.
     */
    void save(Marca marca);

    /**
     * Elimina del sistema la marca asociada al identificador proporcionado.
     * @param id Identificador de la marca a eliminar.
     */
    void deleteById(Long id);
}