package ar.edu.utn.frba.dds.domain.colaboraciones;

import ar.edu.utn.frba.dds.domain.colaboraciones.calculadores.CalculadorDePuntos;
import ar.edu.utn.frba.dds.domain.colaboraciones.calculadores.CalculadorPuntosColocacionHeladera;
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
    this.calculadorDePuntos = new CalculadorPuntosColocacionHeladera();
  }

  public ColocacionHeladeras(Colaborador colaborador, Heladera heladera) {
    super(colaborador, new CalculadorPuntosColocacionHeladera(), heladera.getFechaPuestaFuncionamiento());
    this.heladera = heladera;
  }

  @Override
  public void efectuar() {
    this.colaborador.agregarColocacionHeladera(this);
  }

  public Integer getMesesActiva() {
    return DateHelper.mesesEntre(this.heladera.getFechaPuestaFuncionamiento(), LocalDate.now());
  }

}