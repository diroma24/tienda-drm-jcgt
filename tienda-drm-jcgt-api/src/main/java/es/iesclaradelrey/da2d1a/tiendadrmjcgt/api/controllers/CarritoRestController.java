package es.iesclaradelrey.da2d1a.tiendadrmjcgt.api.controllers;

import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.dtos.AñadirProductoRequestDto;
import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.services.CarritoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.dtos.AñadirProductoRequestDto;
import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.dtos.CarritoDto;
import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.services.CarritoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CarritoRestController {

    private final CarritoService carritoService;

    // 3.2.2 - Listado de carro de compra[cite: 1]
    @GetMapping
    public ResponseEntity<CarritoDto> obtenerCarrito(Authentication authentication) {
        return ResponseEntity.ok(carritoService.obtenerCarritoCompletoDto(authentication.getName()));
    }

    // 3.2.1 - Añadir producto al carro[cite: 1]
    @PostMapping
    public ResponseEntity<CarritoDto> añadirProducto(
            @RequestBody AñadirProductoRequestDto request,
            Authentication authentication) {
        carritoService.añadirProductoAlCarrito(authentication.getName(), request.getProductoId(), request.getUnidades());
        return ResponseEntity.ok(carritoService.obtenerCarritoCompletoDto(authentication.getName()));
    }

    // 3.2.3 - Eliminar producto del carro[cite: 1]
    @DeleteMapping("/{productId}")
    public ResponseEntity<CarritoDto> eliminarProducto(
            @PathVariable Long productId,
            Authentication authentication) {
        carritoService.eliminarProductoDelCarrito(authentication.getName(), productId);
        return ResponseEntity.ok(carritoService.obtenerCarritoCompletoDto(authentication.getName()));
    }

    // 3.2.4 - Vaciar carro de compra[cite: 1]
    @DeleteMapping
    public ResponseEntity<CarritoDto> vaciarCarrito(Authentication authentication) {
        carritoService.vaciarCarrito(authentication.getName());
        return ResponseEntity.ok(carritoService.obtenerCarritoCompletoDto(authentication.getName()));
    }
}