package es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.services;

import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities.Producto;
import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.repositories.ProductoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * Implementación de los servicios de negocio para la gestión de productos.
 * <p>
 * Proporciona la lógica operativa necesaria para manipular los datos de los productos,
 * apoyándose en el repositorio de persistencia correspondiente.
 * </p>
 */
@Service // Identifica esta clase como un componente de servicio en el contexto de Spring
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    /**
     * Constructor para la inyección de dependencias del repositorio de productos.
     */
    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    /**
     * Obtiene todos los productos almacenados en la base de datos.
     */
    @Override
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    /**
     * Localiza un producto por su identificador único. Retorna null si no se encuentra.
     */
    @Override
    public Producto findById(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    /**
     * Gestiona la persistencia de un producto (creación o actualización).
     * Garantiza la integridad de la operación mediante el uso de transacciones.
     * @throws RuntimeException si ocurre un error durante el proceso de guardado.
     */
    @Override
    @Transactional
    public void save(Producto producto) {
        try {
            productoRepository.save(producto);
        } catch (Exception e) {
            throw new RuntimeException("No se ha podido guardar el producto: " + e.getMessage());
        }
    }

    /**
     * Realiza el borrado de un producto tras validar su existencia en el sistema.
     * @throws RuntimeException si el producto no existe o si posee dependencias que impiden su eliminación.
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