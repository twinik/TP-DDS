package ar.edu.utn.frba.dds.domain.incidentes;

import ar.edu.utn.frba.dds.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.domain.notifications.NotificationStrategy;
import ar.edu.utn.frba.dds.domain.notifications.NotificationStrategyFactory;
import ar.edu.utn.frba.dds.helpers.TecnicosHelper;
import ar.edu.utn.frba.dds.repositories.ITecnicosRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Alerta Class representa
 */
@Getter
public class Alerta extends Incidente {
  private TipoAlerta tipoAlerta;

  public Alerta(Heladera heladera, LocalDateTime timestamp, TecnicosHelper tecnicosHelper, NotificationStrategyFactory notificationStrategyFactory, TipoAlerta tipoAlerta) {
    super(heladera, timestamp, tecnicosHelper, notificationStrategyFactory);
    this.tipoAlerta = tipoAlerta;
  }

  public Alerta(TipoAlerta tipoAlerta) {
    this.tipoAlerta = tipoAlerta;
  }

  public static Alerta of(Heladera heladera, LocalDateTime timeStamp, TecnicosHelper tecnicosHelper, NotificationStrategyFactory notificationStrategyFactory, TipoAlerta tipoAlerta) {
    return new Alerta(heladera, timeStamp, tecnicosHelper,notificationStrategyFactory, tipoAlerta);
  }

  public static Alerta of(Heladera heladera,TecnicosHelper tecnicosHelper,NotificationStrategyFactory notificationStrategyFactory, TipoAlerta tipoAlerta) {
    return Alerta.of(heladera, LocalDateTime.now(), tecnicosHelper,notificationStrategyFactory, tipoAlerta);
  }
}