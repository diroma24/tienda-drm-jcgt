package es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.services;

import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities.TipoEventoSeguridad;

/**
 * Interfaz que define los servicios de auditoría del sistema.
 * Proporciona la lógica necesaria para persistir y gestionar eventos de seguridad.
 */
public interface AuditoriaService {

    /**
     * Registra un nuevo evento de seguridad en el sistema.
     * * @param username Nombre del usuario que genera el evento.
     * @param tipo Tipo de evento de seguridad ocurrido.
     * @param ip Dirección IP desde la que se realiza la acción.
     */
    void registrarEvento(String username, TipoEventoSeguridad tipo, String ip);
}