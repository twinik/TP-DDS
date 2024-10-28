package ar.edu.utn.frba.dds.models.domain.incidentes;

import ar.edu.utn.frba.dds.helpers.TecnicosHelper;
import ar.edu.utn.frba.dds.models.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.models.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.models.domain.notifications.NotificationStrategyFactory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDateTime;

/**
 *
 */
@Getter
@Setter
@AllArgsConstructor
@Entity
@DiscriminatorValue("falla_tecnica")
@NoArgsConstructor
public class FallaTecnica extends Incidente {
  @ManyToOne
  @JoinColumn(name = "colaborador_id", referencedColumnName = "id")
  private Colaborador colaborador;
  @Column(name = "descripcion", columnDefinition = "TEXT")
  private String descripcion;
  @Column(name = "url_foto", columnDefinition = "TEXT")
  private String urlFoto;

  public FallaTecnica(Heladera heladera, LocalDateTime timestamp, TecnicosHelper tecnicosHelper, NotificationStrategyFactory notificationStrategyFactory, Colaborador colaborador, String descripcion, String urlFoto) {
    super(heladera, timestamp, tecnicosHelper, notificationStrategyFactory);
    this.colaborador = colaborador;
    this.descripcion = descripcion;
    this.urlFoto = urlFoto;
  }

  @Override
  public String getTipo() {
    return "Falla técnica";
  }
}