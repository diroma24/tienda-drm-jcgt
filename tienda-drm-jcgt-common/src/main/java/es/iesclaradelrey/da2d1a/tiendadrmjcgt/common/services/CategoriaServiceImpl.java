package es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.services;

import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities.Categoria;
import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.repositories.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

/**
 * Implementación de los servicios de negocio para la entidad {@link Categoria}.
 * <p>
 * Esta clase sirve como punto de entrada para todas las operaciones lógicas
 * relacionadas con las categorías, delegando la persistencia de datos al
 * repositorio correspondiente.
 * </p>
 */
@Service
public class CategoriaServiceImpl implements CategoriaService {

    /**
     * Referencia al repositorio de categorías.
     * Se utiliza la interfaz {@link CategoriaRepository}
     */
    private final CategoriaRepository categoriaRepository;

    /**
     * Constructor para la inyección de dependencias.
     * <p>
     * Spring inyectará automáticamente una implementación válida de
     * {@link CategoriaRepository} al instanciar este servicio.
     * </p>
     *
     * @param categoriaRepository El repositorio que se utilizará para el acceso a datos.
     */
    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public void add(Categoria categoria) {
        categoriaRepository.add(categoria);
    }

    @Override
    public Collection<Categoria> getAll() {
        return categoriaRepository.getAll();
    }

    @Override
    public Optional<Categoria> getById(Long id) {
        return categoriaRepository.getById(id);
    }

    @Override
    public void update(Categoria categoria) {
        categoriaRepository.update(categoria);
    }

    @Override
    public void delete(Long id) {
        categoriaRepository.delete(id);
    }
}