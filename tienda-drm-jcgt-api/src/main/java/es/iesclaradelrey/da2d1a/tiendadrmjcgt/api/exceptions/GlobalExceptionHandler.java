package es.iesclaradelrey.da2d1a.tiendadrmjcgt.api.exceptions;

import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.net.URI;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductoNoEncontradoException.class)
    public ProblemDetail handleProductoNoEncontrado(ProductoNoEncontradoException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problemDetail.setTitle("Producto no encontrado");
        return problemDetail;
    }

    @ExceptionHandler(UnidadesNoValidasException.class)
    public ProblemDetail handleUnidadesNoValidas(UnidadesNoValidasException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        problemDetail.setTitle("Petición incorrecta");
        return problemDetail;
    }

    @ExceptionHandler(StockInsuficienteException.class)
    public ProblemDetail handleStockInsuficiente(StockInsuficienteException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.getMessage());
        problemDetail.setTitle("Conflicto de stock");
        return problemDetail;
    }
}