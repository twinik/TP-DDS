package ar.edu.utn.frba.dds.domain.colaboraciones;

import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.domain.helpers.DateHelper;
import lombok.AllArgsConstructor;
import java.time.LocalDate;

/**
 *
 */
@AllArgsConstructor
public class ColocacionHeladeras extends Colaboracion {

  private Heladera heladera;

  /**
   * Default constructor
   */
  public ColocacionHeladeras() {
  }

  @Override
  public void efectuar() {
    this.colaborador.agregarColocacionHeladera(this);
  }

  public Integer getMesesActiva() {
    return DateHelper.mesesEntre(this.heladera.getFechaPuestaFuncionamiento(), LocalDate.now());
  }

  /**
   *
   */

  /**
   *
   */


}