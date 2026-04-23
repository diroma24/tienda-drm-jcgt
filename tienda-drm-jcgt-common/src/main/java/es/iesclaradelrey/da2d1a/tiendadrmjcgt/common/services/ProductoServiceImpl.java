package es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.services;

import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities.Producto;
import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.repositories.ProductoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * Implementación de los servicios de negocio para la entidad {@link Producto}.
 */
@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    @Override
    public Producto findById(Long id) {
        // Es mejor lanzar una excepción o gestionar el Optional correctamente
        return productoRepository.findById(id).orElse(null);
    }

    /**
     * REQUISITO 3.3 y 3.4: Guarda un producto (nuevo o editado).
     * @Transactional asegura que si algo falla en la BD, se haga rollback.
     */
    @Override
    @Transactional
    public void save(Producto producto) {
        try {
            productoRepository.save(producto);
        } catch (Exception e) {
            // Re-lanzamos la excepción para que el controlador la capture (punto 3.3)
            throw new RuntimeException("No se ha podido guardar el producto: " + e.getMessage());
        }
    }

    /**
     * REQUISITO 3.5: Elimina un producto por su ID.
     */
    @Override
    @Transactional
    public void deleteById(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new RuntimeException("El producto con ID " + id + " no existe.");
        }
        try {
            productoRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el producto. Puede que tenga dependencias.");
        }
    }
}