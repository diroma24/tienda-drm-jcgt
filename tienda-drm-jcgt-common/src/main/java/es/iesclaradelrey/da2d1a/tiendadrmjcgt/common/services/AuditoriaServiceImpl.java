package es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.services;

import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities.EventoSeguridad;
import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.entities.TipoEventoSeguridad;
import es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.repositories.EventoSeguridadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class AuditoriaServiceImpl implements AuditoriaService {

    @Autowired
    private EventoSeguridadRepository repository;

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