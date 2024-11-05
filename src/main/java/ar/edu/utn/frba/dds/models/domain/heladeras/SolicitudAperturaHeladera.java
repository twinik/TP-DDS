package ar.edu.utn.frba.dds.models.domain.heladeras;

import ar.edu.utn.frba.dds.models.db.EntidadPersistente;
import ar.edu.utn.frba.dds.models.domain.colaboraciones.RedistribucionViandas;
import ar.edu.utn.frba.dds.models.domain.colaboradores.Colaborador;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * Registra una solicud de apertura por parte del colaborador
 */
@Entity
@Table(name = "solicitud_apertura_heladera")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SolicitudAperturaHeladera extends EntidadPersistente {

  @ManyToOne
  @JoinColumn(name = "colaborador_id", referencedColumnName = "id")
  private Colaborador colaborador;

  @Column(name = "motivo", columnDefinition = "TEXT")
  private String motivo;

  @Column(name = "timestamp", columnDefinition = "DATETIME")
  private LocalDateTime timestamp;

  @ManyToOne
  @JoinColumn(name = "heladera_id", referencedColumnName = "id")
  private Heladera heladera;

  @ManyToOne
  @JoinColumn(name = "redistribucion_id", referencedColumnName = "id")
  private RedistribucionViandas redistribucionViandas;

  @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
  @JoinColumn(name = "ingreso_vianda_id", referencedColumnName = "id")
  private IngresoVianda viandas;

  public boolean esDonacionDeViandas() {
    return this.viandas != null && !this.viandas.getViandas().isEmpty();
  }

}