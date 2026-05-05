package es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities;

/**
 * Enumerado que define los distintos tipos de eventos de seguridad que pueden ocurrir en el sistema.
 * Se utiliza principalmente para clasificar los registros de auditoría en la entidad EventoSeguridad.
 */
public enum TipoEventoSeguridad {

    /**
     * Indica que un usuario ha iniciado sesión correctamente en el sistema.
     */
    LOGIN_EXITOSO,

    /**
     * Indica que ha habido un intento fallido de inicio de sesión (e.g., contraseña incorrecta).
     */
    LOGIN_FALLIDO,

    /**
     * Indica que un usuario ha cerrado su sesión de forma voluntaria.
     */
    LOGOUT
}