package es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.services;

import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.dtos.CarritoDto;
import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.dtos.LineaCarritoDto;
import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities.LineaCarrito;
import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities.Producto;
import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities.Usuario;
import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.exceptions.ProductoNoEncontradoException;
import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.exceptions.StockInsuficienteException;
import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.exceptions.UnidadesNoValidasException;
import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.repositories.LineaCarritoRepository;
import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.repositories.ProductoRepository;
import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarritoServiceImpl implements CarritoService {

    private final LineaCarritoRepository lineaCarritoRepository;
    private final ProductoRepository productoRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    public List<LineaCarrito> obtenerCarritoPorUsuario(String nombreUsuario) {
        Usuario usuario = usuarioRepository.findByUsername(nombreUsuario).orElseThrow();
        return lineaCarritoRepository.findByUsuario(usuario);
    }

    @Override
    @Transactional
    public void vaciarCarrito(String nombreUsuario) {
        Usuario usuario = usuarioRepository.findByUsername(nombreUsuario).orElseThrow();
        lineaCarritoRepository.deleteByUsuario(usuario);
    }

    @Override
    public Long obtenerConteoProductosDistintos(String nombreUsuario) {
        Usuario usuario = usuarioRepository.findByUsername(nombreUsuario).orElseThrow();
        return lineaCarritoRepository.contarProductosDistintosPorUsuario(usuario);
    }

    @Override
    public Long obtenerSumaUnidadesTotales(String nombreUsuario) {
        Usuario usuario = usuarioRepository.findByUsername(nombreUsuario).orElseThrow();
        return lineaCarritoRepository.sumarUnidadesTotalesPorUsuario(usuario);
    }

    @Override
    public Double obtenerImporteTotal(String nombreUsuario) {
        Usuario usuario = usuarioRepository.findByUsername(nombreUsuario).orElseThrow();
        Double total = lineaCarritoRepository.calcularImporteTotalPorUsuario(usuario);
        return (total != null) ? total : 0.0;
    }
    @Override
    @Transactional
    public LineaCarrito añadirProductoAlCarrito(String nombreUsuario, Long productoId, Integer unidades) {
        // 1. Verificación de unidades
        if (unidades <= 0) {
            throw new UnidadesNoValidasException("Debes añadir al menos una unidad.");
        }

        Usuario usuario = usuarioRepository.findByUsername(nombreUsuario).orElseThrow();

        // 2. Verificación de existencia del producto
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new ProductoNoEncontradoException(productoId));

        // 3. Verificación de stock[cite: 1]
        if (producto.getStock() < unidades) {
            throw new StockInsuficienteException("Stock insuficiente para el producto: " + producto.getNombre());
        }

        LineaCarrito linea = lineaCarritoRepository.findByUsuarioAndProducto(usuario, producto)
                .orElse(LineaCarrito.builder()
                        .usuario(usuario)
                        .producto(producto)
                        .unidades(0)
                        .build());

        linea.setUnidades(linea.getUnidades() + unidades);
        return lineaCarritoRepository.save(linea);
    }
    @Override
    @Transactional
    public void eliminarProductoDelCarrito(String nombreUsuario, Long productoId) {
        Usuario usuario = usuarioRepository.findByUsername(nombreUsuario).orElseThrow();

        // Verificación: Existe el producto
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new ProductoNoEncontradoException(productoId));

        // Verificación: El producto está en el carro[cite: 1]
        LineaCarrito linea = lineaCarritoRepository.findByUsuarioAndProducto(usuario, producto)
                .orElseThrow(() -> new StockInsuficienteException("El producto no está en el carrito"));
        // Nota: Se lanza excepción de conflicto (409) como pide el 3.2.3[cite: 1]

        lineaCarritoRepository.deleteByUsuarioAndProducto(usuario, producto);
    }
    @Override
    public CarritoDto obtenerCarritoCompletoDto(String nombreUsuario) {
        // 1. Buscamos las líneas de la BD[cite: 1]
        List<LineaCarrito> lineas = this.obtenerCarritoPorUsuario(nombreUsuario);

        // 2. Mapeamos a DTOs de línea[cite: 1]
        List<LineaCarritoDto> lineasDto = lineas.stream().map(linea -> {
            double precioBase = linea.getProducto().getPrecio();
            int dto = linea.getProducto().getDescuento();
            double precioConDescuento = precioBase * (1 - dto / 100.0);

            return LineaCarritoDto.builder()
                    .nombreProducto(linea.getProducto().getNombre())
                    .precioUnitario(precioBase)
                    .descuento(dto)
                    .precioConDescuento(precioConDescuento)
                    .unidades(linea.getUnidades())
                    .precioTotalLinea(precioConDescuento * linea.getUnidades())
                    .build();
        }).toList();

        // 3. Montamos el DTO principal con los totales del repositorio[cite: 1]
        return CarritoDto.builder()
                .lineas(lineasDto)
                .numeroProductosDistintos(this.obtenerConteoProductosDistintos(nombreUsuario))
                .numeroUnidadesTotales(this.obtenerSumaUnidadesTotales(nombreUsuario))
                .importeTotal(this.obtenerImporteTotal(nombreUsuario))
                .build();
    }

}
