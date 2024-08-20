package ar.edu.utn.frba.dds.domain.colaboraciones;

import ar.edu.utn.frba.dds.domain.colaboraciones.utils.MotivoRedistribucionVianda;
import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.domain.heladeras.Heladera;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * RedistribucionViandas class representa una colaboracion de un colaborador.
 * Representa el movimiento de una vianda entre heladeras.
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RedistribucionViandas implements IPuntajeCalculable {

  private Long id;
  private Colaborador colaborador;
  private LocalDate fecha;
  private Heladera heladeraOrigen;
  private Heladera heladeraDestino;
  private MotivoRedistribucionVianda motivo;
  private Integer cantidad;

  @Override
  public Float calcularPuntaje() {
    return (float) this.cantidad;
  }
}