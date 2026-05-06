package es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.exceptions;

public class StockInsuficienteException extends RuntimeException {
    public StockInsuficienteException(String mensaje) {
        super(mensaje);
    }
}
