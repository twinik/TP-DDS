package ar.edu.utn.frba.dds.domain.incidentes;

import ar.edu.utn.frba.dds.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.domain.notifications.NotificationStrategyFactory;
import ar.edu.utn.frba.dds.helpers.TecnicosHelper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

/**
 * Alerta Class representa
 */
@Getter
@Entity
@DiscriminatorValue("alerta")
@NoArgsConstructor
public class Alerta extends Incidente {
  @Enumerated(EnumType.STRING)
  @Column(name = "tipo_alterta")
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