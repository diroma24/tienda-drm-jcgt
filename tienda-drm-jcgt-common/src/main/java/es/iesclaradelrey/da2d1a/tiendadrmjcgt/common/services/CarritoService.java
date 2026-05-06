package es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.services;

import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.dtos.CarritoDto;
import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities.LineaCarrito;
import java.util.List;

public interface CarritoService {
    // Métodos de modificación
    LineaCarrito añadirProductoAlCarrito(String nombreUsuario, Long productoId, Integer unidades);
    void eliminarProductoDelCarrito(String nombreUsuario, Long productoId);
    void vaciarCarrito(String nombreUsuario);

    // Métodos de consulta
    List<LineaCarrito> obtenerCarritoPorUsuario(String nombreUsuario);
    CarritoDto obtenerCarritoCompletoDto(String nombreUsuario); // <-- AÑADIDO ESTO

    // Métodos para los totales (usados internamente o por el DTO)
    Long obtenerConteoProductosDistintos(String nombreUsuario);
    Long obtenerSumaUnidadesTotales(String nombreUsuario);
    Double obtenerImporteTotal(String nombreUsuario);
}