package ar.edu.utn.frba.dds.domain.incidentes;

import ar.edu.utn.frba.dds.domain.excepciones.InvalidNotificationStrategyException;
import ar.edu.utn.frba.dds.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.domain.notifications.NotificationStrategy;
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

  public Incidente() {
  }

  public void reportar() {
    heladera.inhabilitar();
    Tecnico tecnicoAContactar = TecnicosHelper.findTecnicoMasCercano(heladera.getUbicacion());
    String message = String.format("Hola %s se rompio la heladera %s a las %s y necesitamos que por favor venga a repararla",
        tecnicoAContactar.getNombre(), heladera.getNombre(), timestamp.toString());
    tecnicoAContactar.getMedioContacto().stream().parallel().forEach(medio -> {
      NotificationStrategy strategy = NotificationStrategyFactory.create(medio.getCanal());
      if (strategy == null) {
        throw new InvalidNotificationStrategyException();
      }
      strategy.notificar(medio, message);
    });
  }

}

//Mensaje: clase, interfaz, distintos factorys, no un string d mierda
//InterfazMensaje