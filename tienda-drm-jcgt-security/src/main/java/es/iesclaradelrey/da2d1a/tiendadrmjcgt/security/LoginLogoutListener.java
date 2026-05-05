package es.iesclaradelrey.da2d1a.tiendadrmjcgt.security;

import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities.TipoEventoSeguridad;
import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.services.AuditoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.authentication.event.LogoutSuccessEvent;
import org.springframework.stereotype.Component;

/**
 * Componente que escucha y gestiona los eventos de autenticación de Spring Security.
 * Intercepta éxitos, fallos y cierres de sesión para disparar el registro de auditoría.
 */
@Component
public class LoginLogoutListener {

    /**
     * Servicio encargado de persistir los eventos en el registro de auditoría.
     */
    @Autowired
    private AuditoriaService auditoriaService;

    /**
     * Escucha y procesa los eventos de inicio de sesión exitoso.
     * @param event Evento generado automáticamente por Spring Security al autenticar un usuario.
     */
    @EventListener
    public void onSuccess(AuthenticationSuccessEvent event) {
        String username = event.getAuthentication().getName();
        auditoriaService.registrarEvento(username, TipoEventoSeguridad.LOGIN_EXITOSO, "IP_LOCAL");
    }

    /**
     * Escucha y procesa los intentos de inicio de sesión fallidos por credenciales erróneas.
     * @param event Evento generado cuando la autenticación falla.
     */
    @EventListener
    public void onFailure(AuthenticationFailureBadCredentialsEvent event) {
        String username = (String) event.getAuthentication().getPrincipal();
        auditoriaService.registrarEvento(username, TipoEventoSeguridad.LOGIN_FALLIDO, "IP_LOCAL");
    }

    /**
     * Escucha y procesa los eventos de cierre de sesión completado.
     * @param event Evento generado tras invalidar con éxito la sesión del usuario.
     */
    @EventListener
    public void onLogoutSuccess(LogoutSuccessEvent event) {
        if (event.getAuthentication() != null) {
            String username = event.getAuthentication().getName();
            auditoriaService.registrarEvento(username, TipoEventoSeguridad.LOGOUT, "IP_LOCAL");
        }
    }
}