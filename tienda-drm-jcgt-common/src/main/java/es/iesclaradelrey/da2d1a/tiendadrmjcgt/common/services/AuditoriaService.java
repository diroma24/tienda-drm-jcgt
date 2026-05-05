package es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.services;

import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities.TipoEventoSeguridad;

public interface AuditoriaService {
    void registrarEvento(String username, TipoEventoSeguridad tipo, String ip);
}