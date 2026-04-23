package es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.services;

import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities.Marca;
import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.repositories.MarcaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * Implementación de los servicios de negocio para la gestión de marcas.
 * <p>
 * Proporciona la lógica operativa necesaria para manipular los datos de las marcas,
 * apoyándose en el repositorio de persistencia correspondiente.
 * </p>
 */
@Service // Identifica esta clase como un componente de servicio en el contexto de Spring
public class MarcaServiceImpl implements MarcaService {

    private final MarcaRepository marcaRepository;

    /**
     * Constructor para la inyección de dependencias del repositorio de marcas.
     */
    public MarcaServiceImpl(MarcaRepository marcaRepository) {
        this.marcaRepository = marcaRepository;
    }

    /**
     * Obtiene todas las marcas almacenadas en la base de datos.
     */
    @Override
    public List<Marca> findAll() {
        return marcaRepository.findAll();
    }

    /**
     * Localiza una marca por su identificador único. Retorna null si no se encuentra.
     */
    @Override
    public Marca findById(Long id) {
        return marcaRepository.findById(id).orElse(null);
    }

    /**
     * Gestiona la persistencia de una marca (creación o actualización).
     * Garantiza la integridad de la operación mediante el uso de transacciones.
     * @throws RuntimeException si ocurre un error durante el proceso de guardado.
     */
    @Override
    @Transactional
    public void save(Marca marca) {
        try {
            marcaRepository.save(marca);
        } catch (Exception e) {
            throw new RuntimeException("Error al procesar la marca: " + e.getMessage());
        }
    }

    /**
     * Realiza el borrado lógico o físico de una marca tras validar su existencia.
     * @throws RuntimeException si la marca no existe o si posee productos vinculados.
     */
    @Override
    @Transactional
    public void deleteById(Long id) {
        if (!marcaRepository.existsById(id)) {
            throw new RuntimeException("La marca seleccionada no existe.");
        }
        try {
            marcaRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("No se puede eliminar la marca porque hay productos vinculados a ella.");
        }
    }
}