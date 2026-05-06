package es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.repositories;

import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities.LineaCarrito;
import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities.Producto;
import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LineaCarritoRepository extends JpaRepository<LineaCarrito, Long> {

    // Obtener todas las líneas del carro de un usuario[cite: 1]
    List<LineaCarrito> findByUsuario(Usuario usuario);

    // Buscar si un producto ya existe en el carro del usuario para no duplicarlo[cite: 1]
    Optional<LineaCarrito> findByUsuarioAndProducto(Usuario usuario, Producto producto);

    // Eliminar un producto concreto del carro[cite: 1]
    @Modifying
    void deleteByUsuarioAndProducto(Usuario usuario, Producto producto);

    // Vaciar el carro completo[cite: 1]
    @Modifying
    void deleteByUsuario(Usuario usuario);

    // --- Consultas JPQL para el DTO del listado (Requisito 3.2.2) ---

    // Número de productos distintos[cite: 1]
    @Query("SELECT COUNT(l) FROM LineaCarrito l WHERE l.usuario = :usuario")
    Long contarProductosDistintosPorUsuario(@Param("usuario") Usuario usuario);

    // Número de unidades totales[cite: 1]
    @Query("SELECT SUM(l.unidades) FROM LineaCarrito l WHERE l.usuario = :usuario")
    Long sumarUnidadesTotalesPorUsuario(@Param("usuario") Usuario usuario);

    // Importe total calculando precio con descuento por unidades[cite: 1]
    @Query("SELECT SUM(l.unidades * (l.producto.precio * (1 - l.producto.descuento / 100.0))) " +
            "FROM LineaCarrito l WHERE l.usuario = :usuario")
    Double calcularImporteTotalPorUsuario(@Param("usuario") Usuario usuario);
}
