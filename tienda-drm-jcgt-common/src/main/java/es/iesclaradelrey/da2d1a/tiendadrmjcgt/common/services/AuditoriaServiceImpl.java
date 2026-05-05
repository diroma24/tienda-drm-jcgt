package es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.services;

import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities.EventoSeguridad;
import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities.TipoEventoSeguridad;
import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.repositories.EventoSeguridadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

/**
 * Implementación del servicio de auditoría.
 * Se encarga de la lógica de negocio para la creación y persistencia de eventos de seguridad
 * utilizando el repositorio correspondiente.
 */
@Service
public class AuditoriaServiceImpl implements AuditoriaService {

    /**
     * Repositorio para la gestión de la persistencia de los eventos de seguridad.
     */
    @Autowired
    private EventoSeguridadRepository repository;

    /**
     * Registra un nuevo evento de seguridad, construyendo la entidad y almacenándola en la base de datos.
     * * @param username Nombre del usuario que genera el evento.
     * @param tipo Tipo de evento de seguridad ocurrido.
     * @param ip Dirección IP desde la que se realiza la acción.
     */
    @Override
    public void registrarEvento(String username, TipoEventoSeguridad tipo, String ip) {
        EventoSeguridad evento = EventoSeguridad.builder()
                .username(username)
                .tipoEvento(tipo)
                .fechaHora(LocalDateTime.now())
                .ip(ip)
                .build();

        repository.save(evento);
    }
}