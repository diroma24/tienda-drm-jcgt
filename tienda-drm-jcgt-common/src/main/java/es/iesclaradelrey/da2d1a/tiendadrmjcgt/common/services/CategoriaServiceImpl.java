package es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.services;

import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities.Categoria;
import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.repositories.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

/**
 * Implementación de los servicios de negocio para la entidad {@link Categoria}.
 * </p>
 */
@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public void add(Categoria categoria) {
        categoriaRepository.save(categoria);
    }

    @Override
    public Collection<Categoria> getAll() {
        return categoriaRepository.findAll();
    }

    @Override
    public Optional<Categoria> getById(Long id) {
        return categoriaRepository.findById(id);
    }

    @Override
    public void update(Categoria categoria) {
        categoriaRepository.save(categoria);
    }

    @Override
    public void delete(Long id) {
        categoriaRepository.deleteById(id);
    }
}