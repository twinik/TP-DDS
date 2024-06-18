package ar.edu.utn.frba.dds.domain.incidentes;

import ar.edu.utn.frba.dds.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.domain.notifications.NotificationStrategy;
import lombok.Getter;
import java.time.LocalDateTime;

/**
 * Representa un incidente
 */
@Getter
public class Incidente {
    private Heladera heladera;
    private LocalDateTime timestamp;
    private NotificationStrategy notificationStrategy;

    public void reportar() {
        // TODO implement here
    }

}