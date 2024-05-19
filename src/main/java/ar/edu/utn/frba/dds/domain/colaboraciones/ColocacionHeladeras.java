package ar.edu.utn.frba.dds.domain.colaboraciones;

import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.domain.heladeras.Heladera;
import java.time.LocalDate;

/**
 *
 */
public class ColocacionHeladeras extends Colaboracion {

  private LocalDate fechaColocacion;
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
    LocalDate hoy = LocalDate.now();
    Integer difAnios = hoy.getYear() - this.fechaColocacion.getYear();
    Integer difMeses = hoy.getMonthValue() - this.fechaColocacion.getMonthValue();
    return difAnios + difMeses;
  }

  /**
   *
   */

  /**
   *
   */


}