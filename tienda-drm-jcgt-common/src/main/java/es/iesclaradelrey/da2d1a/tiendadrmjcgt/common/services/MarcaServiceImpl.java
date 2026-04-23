package es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.services;

import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities.Categoria;
import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities.Marca;
import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.repositories.MarcaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Implementación de los servicios de negocio para la entidad {@link Marca}.
 * </p>
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
        // Se .orElse(null) para evitar errores si no encuentra la marca
        return marcaRepository.findById(id).orElse(null);
    }
}