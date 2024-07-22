package ar.edu.utn.frba.dds.domain.incidentes;

import ar.edu.utn.frba.dds.domain.excepciones.InvalidNotificationStrategyException;
import ar.edu.utn.frba.dds.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.domain.notifications.NotificationStrategy;
import ar.edu.utn.frba.dds.messageFactory.MessageFactory;
import lombok.Getter;
import ar.edu.utn.frba.dds.domain.notifications.NotificationStrategyFactory;
import ar.edu.utn.frba.dds.domain.tecnicos.Tecnico;
import ar.edu.utn.frba.dds.domain.utils.MedioDeContacto;
import ar.edu.utn.frba.dds.helpers.TecnicosHelper;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

/**
 * Representa un incidente
 */
@Getter
@AllArgsConstructor
public class Incidente {
  private Heladera heladera;
  private LocalDateTime timestamp;
  private TecnicosHelper tecnicosHelper;
  private NotificationStrategyFactory notificationStrategyFactory;

  public Incidente() {
  }

  public void reportar() {
    heladera.inhabilitar();
    Tecnico tecnicoAContactar = tecnicosHelper.findTecnicoMasCercano(heladera.getUbicacion());
    String message = MessageFactory.generarMensajeParaTecnicosPorIncidente(tecnicoAContactar,heladera,timestamp);
    tecnicoAContactar.getMedioContacto().stream().parallel().forEach(medio -> {
      NotificationStrategy strategy = notificationStrategyFactory.create(medio.getCanal());
      if (strategy == null) {
        throw new InvalidNotificationStrategyException();
      }
      strategy.notificar(medio, message);
    });
  }

}

//Mensaje: clase, interfaz, distintos factorys, no un string d mierda
//InterfazMensaje