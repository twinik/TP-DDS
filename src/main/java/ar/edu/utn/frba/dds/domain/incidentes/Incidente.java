package ar.edu.utn.frba.dds.domain.incidentes;

import ar.edu.utn.frba.dds.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.domain.notifications.NotificationStrategy;
import ar.edu.utn.frba.dds.messageFactory.MensajeTecnicosIncidenteFactory;
import lombok.Getter;
import ar.edu.utn.frba.dds.domain.notifications.NotificationStrategyFactory;
import ar.edu.utn.frba.dds.domain.tecnicos.Tecnico;
import ar.edu.utn.frba.dds.helpers.TecnicosHelper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * Representa un incidente
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Incidente {
  private Long id;
  private Heladera heladera;
  private LocalDateTime timestamp;
  private TecnicosHelper tecnicosHelper;
  private NotificationStrategyFactory notificationStrategyFactory;

  public Incidente(Heladera heladera, LocalDateTime timestamp, TecnicosHelper tecnicosHelper, NotificationStrategyFactory notificationStrategyFactory) {
    this.heladera = heladera;
    this.timestamp = timestamp;
    this.tecnicosHelper = tecnicosHelper;
    this.notificationStrategyFactory = notificationStrategyFactory;
  }

  public void reportar() {
    heladera.inhabilitar();
    Tecnico tecnicoAContactar = tecnicosHelper.findTecnicoMasCercano(heladera.getUbicacion());
    String message = MensajeTecnicosIncidenteFactory.generarMensaje(tecnicoAContactar, heladera, timestamp);
    tecnicoAContactar.getMedioContacto().stream().parallel().forEach(medio -> {
      NotificationStrategy strategy = notificationStrategyFactory.create(medio.getCanal());
      strategy.notificar(tecnicoAContactar, message);
    });
  }

}
