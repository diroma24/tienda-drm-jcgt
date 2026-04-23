package es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.services;

import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities.Marca;
import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.repositories.MarcaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * Implementación de los servicios de negocio para la entidad {@link Marca}.
 */
@Service
public class MarcaServiceImpl implements MarcaService {

    private final MarcaRepository marcaRepository;

    public MarcaServiceImpl(MarcaRepository marcaRepository) {
        this.marcaRepository = marcaRepository;
    }

    @Override
    public List<Marca> findAll() {
        return marcaRepository.findAll();
    }

    @Override
    public Marca findById(Long id) {
        return marcaRepository.findById(id).orElse(null);
    }

    /**
     * REQUISITO 3.3 y 3.4: Guarda una marca.
     * Al usar .save(), JPA detecta si tiene ID (actualiza) o no (crea).
     */
    @Override
    @Transactional
    public void save(Marca marca) {
        try {
            marcaRepository.save(marca);
        } catch (Exception e) {
            // Lanzamos excepción para que el AdminController capture el error (Requisito 3.3)
            throw new RuntimeException("Error al procesar la marca: " + e.getMessage());
        }
    }

    /**
     * REQUISITO 3.5: Borrado de marca.
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
            // Típico error si la marca tiene productos asociados
            throw new RuntimeException("No se puede eliminar la marca porque hay productos vinculados a ella.");
        }
    }
}