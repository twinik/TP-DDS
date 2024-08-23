package ar.edu.utn.frba.dds.domain.colaboraciones.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * MotivoRedistribucionVianda class representa un motivo de redistribucion de vianda.
 */
@Getter
@AllArgsConstructor
@Entity
@Table(name = "motivo_redistribucion_vianda")
@NoArgsConstructor
public class MotivoRedistribucionVianda {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "motivo_descripcion",columnDefinition = "TEXT")
  private String motivo;

  public MotivoRedistribucionVianda(String motivo) {
    this.motivo = motivo;
  }
}
