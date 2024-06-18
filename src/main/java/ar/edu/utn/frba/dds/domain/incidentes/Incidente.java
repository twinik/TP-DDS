package ar.edu.utn.frba.dds.domain.incidentes;

import ar.edu.utn.frba.dds.domain.excepciones.InvalidNotificationStrategyException;
import ar.edu.utn.frba.dds.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.domain.notifications.NotificationStrategy;
import ar.edu.utn.frba.dds.domain.notifications.NotificationStrategyFactory;
import ar.edu.utn.frba.dds.domain.tecnicos.Tecnico;
import ar.edu.utn.frba.dds.domain.utils.MedioDeContacto;
import ar.edu.utn.frba.dds.helpers.TecnicosHelper;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

/**
 * Representa un incidente
 */
@AllArgsConstructor
public class Incidente {
    private Heladera heladera;
    private LocalDateTime timestamp;

    public Incidente() {}

    public void reportar() {
        heladera.inhabilitar();
        Tecnico tecnicoAContactar = TecnicosHelper.findTecnicoMasCercano(heladera.getUbicacion());
        MedioDeContacto medioDeContacto = tecnicoAContactar.getMedioContacto().get(0);
        NotificationStrategy notificationStrategy = NotificationStrategyFactory.create(medioDeContacto.getCanal());
        if (notificationStrategy == null) {
            throw new InvalidNotificationStrategyException();
        }
        String message = ""; // TODO armar mensaje de aviso de incidente
        notificationStrategy.notificar(medioDeContacto, message);
    }

}