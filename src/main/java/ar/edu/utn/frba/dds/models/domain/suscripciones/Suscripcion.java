package ar.edu.utn.frba.dds.models.domain.suscripciones;

import ar.edu.utn.frba.dds.models.converters.NotificationStrategyAttributeConverter;
import ar.edu.utn.frba.dds.models.converters.TipoSuscripcionAttributeConverter;
import ar.edu.utn.frba.dds.models.db.EntidadPersistente;
import ar.edu.utn.frba.dds.models.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.models.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.models.domain.notifications.NotificationStrategy;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

/**
 * Representa una suscripcion de la cual un colaborador va a recibir notificaciones
 */
@Entity
@Table(name = "suscripcion")
@NoArgsConstructor
@Setter
@Getter
public class Suscripcion extends EntidadPersistente {
  @ManyToOne
  @JoinColumn(name = "colaborador_id", referencedColumnName = "id")
  private Colaborador colaborador;

  @Convert(converter = NotificationStrategyAttributeConverter.class)
  @Column(name = "notification_strategy")
  private NotificationStrategy notificacionStrategy;

  @Convert(converter = TipoSuscripcionAttributeConverter.class)
  @Column(name = "tipo_suscripcion")
  private ITipoSuscripcion tipoSuscripcion;

  @Column(name = "numero")
  private int numero;

  public Suscripcion(Colaborador colaborador, NotificationStrategy notificacionStrategy, ITipoSuscripcion tipoSuscripcion, int numero) {
    this.colaborador = colaborador;
    this.notificacionStrategy = notificacionStrategy;
    this.tipoSuscripcion = tipoSuscripcion;
    this.numero = numero;
  }

  public Suscripcion(Colaborador colaborador, NotificationStrategy notificacionStrategy, ITipoSuscripcion tipoSuscripcion) {
    this.colaborador = colaborador;
    this.notificacionStrategy = notificacionStrategy;
    this.tipoSuscripcion = tipoSuscripcion;
  }

  public void avisarEvento(Heladera heladera) {
    tipoSuscripcion.notificar(heladera, this);
  }

}