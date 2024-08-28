package ar.edu.utn.frba.dds.domain.incidentes;

import ar.edu.utn.frba.dds.converters.NotificationStrategyAttributeConverter;
import ar.edu.utn.frba.dds.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.domain.notifications.NotificationStrategy;
import ar.edu.utn.frba.dds.messageFactory.MensajeTecnicosIncidenteFactory;
import lombok.Getter;
import ar.edu.utn.frba.dds.domain.notifications.NotificationStrategyFactory;
import ar.edu.utn.frba.dds.domain.tecnicos.Tecnico;
import ar.edu.utn.frba.dds.helpers.TecnicosHelper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.time.LocalDateTime;

/**
 * Representa un incidente
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "incidente")
@DiscriminatorColumn(name = "tipo_incidente")
public class Incidente {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "heladera_id",referencedColumnName = "id")
  private Heladera heladera;

  @Column(name = "timestamp",columnDefinition = "TIMESTAMP")
  private LocalDateTime timestamp;

  @Transient
  private TecnicosHelper tecnicosHelper;

  @Transient
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
