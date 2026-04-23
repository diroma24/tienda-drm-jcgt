package es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.services;

import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities.Categoria;
import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.repositories.CategoriaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementación de los servicios de negocio para la gestión de categorías.
 * <p>
 * Proporciona la lógica operativa necesaria para manipular los datos de las categorías,
 * apoyándose en el repositorio de persistencia correspondiente.
 * </p>
 */
@Service // Identifica esta clase como un componente de servicio en el contexto de Spring
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    /**
     * Constructor para la inyección de dependencias del repositorio de categorías.
     */
    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    /**
     * Obtiene todas las categorías almacenadas en la base de datos.
     */
    @Override
    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    /**
     * Localiza una categoría por su identificador. Retorna null si no se encuentra.
     */
    @Override
    public Categoria findById(Long id) {
        return categoriaRepository.findById(id).orElse(null);
    }

    /**
     * Almacena o actualiza la información de una categoría.
     * Garantiza la atomicidad de la operación mediante una transacción.
     * @throws RuntimeException si ocurre un error durante la persistencia.
     */
    @Override
    @Transactional
    public void save(Categoria categoria) {
        try {
            categoriaRepository.save(categoria);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar la categoría: " + e.getMessage());
        }
    }

    /**
     * Elimina una categoría del sistema tras verificar la viabilidad de la operación.
     * @throws RuntimeException si la categoría posee dependencias que impiden su borrado.
     */
    @Override
    @Transactional
    public void deleteById(Long id) {
        try {
            categoriaRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("No se puede borrar la categoría porque tiene productos asociados.");
        }
    }
}