package es.iesclaradelrey.da2d1a.tiendadrmjcgt.security;

import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities.TipoEventoSeguridad;
import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.services.AuditoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.authentication.event.LogoutSuccessEvent;
import org.springframework.stereotype.Component;

@Component
public class LoginLogoutListener {

    @Autowired
    private AuditoriaService auditoriaService;

    // Escucha logins exitosos
    @EventListener
    public void onSuccess(AuthenticationSuccessEvent event) {
        String username = event.getAuthentication().getName();
        auditoriaService.registrarEvento(username, TipoEventoSeguridad.LOGIN_EXITOSO, "IP_LOCAL");
    }

    // Escucha intentos fallidos
    @EventListener
    public void onFailure(AuthenticationFailureBadCredentialsEvent event) {
        String username = (String) event.getAuthentication().getPrincipal();
        auditoriaService.registrarEvento(username, TipoEventoSeguridad.LOGIN_FALLIDO, "IP_LOCAL");
    }

    // Escucha logouts completados
    @EventListener
    public void onLogoutSuccess(LogoutSuccessEvent event) {
        if (event.getAuthentication() != null) {
            String username = event.getAuthentication().getName();
            auditoriaService.registrarEvento(username, TipoEventoSeguridad.LOGOUT, "IP_LOCAL");
        }
    }
}