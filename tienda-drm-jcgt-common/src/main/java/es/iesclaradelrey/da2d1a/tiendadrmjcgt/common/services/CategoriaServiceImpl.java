package es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.services;

import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities.Categoria;
import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.repositories.CategoriaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    @Override
    public Categoria findById(Long id) {
        return categoriaRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void save(Categoria categoria) {
        try {
            categoriaRepository.save(categoria);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar la categoría: " + e.getMessage());
        }
    }

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