package ar.edu.utn.frba.dds.domain.colaboraciones;

import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.helpers.DateHelper;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * ColocacionHeladeras class representa una colaboracion de un colaborador.
 * Consiste en colocar una heladera en un establecimiento.
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ColocacionHeladeras implements IPuntajeCalculable {
  public static final Float PUNTOS_POR_MES = 5.0f;
  private Long id;
  private Colaborador colaborador;
  private LocalDate fecha;
  private Heladera heladera;

  public ColocacionHeladeras(Colaborador colaborador, LocalDate fecha, Heladera heladera) {
    this.colaborador = colaborador;
    this.fecha = fecha;
    this.heladera = heladera;
  }

  /**
   * Metodo getMesesActiva que devuelve la cantidad de meses que la heladera lleva activa.
   */
  public Integer getMesesActiva() {
    return DateHelper.mesesEntre(this.heladera.getFechaPuestaFuncionamiento(), LocalDate.now());
  }

  @Override
  public Float calcularPuntaje() {
    return PUNTOS_POR_MES * this.getMesesActiva();
  }
}
