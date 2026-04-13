package es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.repositories;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Implementación base de tipo memoria para un {@link Repository}.
 * <p>
 * Esta clase abstracta utiliza un {@link HashMap} interno para almacenar las entidades.
 * Es ideal para prototipado rápido, pruebas unitarias o almacenamiento volátil
 * que no requiere una base de datos persistente.
 * </p>
 *
 * @param <T>  El tipo de objeto de dominio que se almacenará.
 * @param <ID> El tipo del identificador único del objeto.
 */
public abstract class MapRepository<T, ID> implements Repository<T, ID> {

    /**
     * Estructura de datos interna que actúa como almacenamiento.
     * La llave es el ID de la entidad y el valor es la entidad completa.
     */
    protected Map<ID, T> data = new HashMap<>();

    /**
     * Agrega una nueva entidad al mapa.
     * <p>
     * <b>Nota:</b> En implementaciones concretas, se debe asegurar que la entidad
     * tenga un ID asignado antes de insertarla en el mapa {@code data}.
     * </p>
     *
     * @param entity La entidad a persistir en memoria.
     */
    @Override
    public void add(T entity) {
        // En una implementación real, aquí se gestionaría la obtención del ID
        // Para el genérico se deja simple
    }

    /**
     * Devuelve todas las entidades almacenadas en el mapa.
     *
     * @return Una colección con todos los valores presentes en el almacenamiento.
     */
    @Override
    public Collection<T> getAll() {
        return data.values();
    }

    /**
     * Busca una entidad mediante su clave en el mapa.
     *
     * @param id El identificador único del objeto.
     * @return Un {@link Optional} conteniendo el objeto, o vacío si el ID no existe.
     */
    @Override
    public Optional<T> getById(ID id) {
        return Optional.ofNullable(data.get(id));
    }

    /**
     * Actualiza una entidad existente en el mapa.
     * <p>
     * Se espera que la entidad proporcionada contenga un ID que ya exista
     * en el mapa para realizar la sobrescritura.
     * </p>
     *
     * @param entity La entidad con los nuevos datos.
     */
    @Override
    public void update(T entity) {
        // Lógica de actualización (generalmente data.put(id, entity))
    }

    /**
     * Elimina una entidad del mapa basándose en su identificador.
     *
     * @param id El ID del objeto que se desea remover.
     */
    @Override
    public void delete(ID id) {
        data.remove(id);
    }
}