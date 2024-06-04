package ar.edu.utn.frba.dds.domain.colaboraciones;

import ar.edu.utn.frba.dds.domain.colaboraciones.calculadores.CalculadorDePuntosFactory;
import ar.edu.utn.frba.dds.domain.colaboraciones.calculadores.CalculadorPuntosColocacionHeladera;
import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.domain.colaboradores.FormaColaboracion;
import ar.edu.utn.frba.dds.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.domain.helpers.DateHelper;
import java.time.LocalDate;
import lombok.AllArgsConstructor;

/**
 * ColocacionHeladeras class representa una colaboracion de un colaborador.
 * Consiste en colocar una heladera en un establecimiento.
 */
@AllArgsConstructor
public class ColocacionHeladeras extends Colaboracion {
  private Heladera heladera;

  /**
   * Constructor por defecto.
   */
  public ColocacionHeladeras() {
    this.calculadorDePuntos = CalculadorDePuntosFactory.create(FormaColaboracion.COLOCACION_HELADERA);
  }

  /**
   * Constructor con parametros.
   */
  public ColocacionHeladeras(Colaborador colaborador, Heladera heladera) {
    super(colaborador, CalculadorDePuntosFactory.create(FormaColaboracion.COLOCACION_HELADERA), heladera.getFechaPuestaFuncionamiento());
    this.heladera = heladera;
  }

  /**
   * Metodo efectuar que se encarga de sumar puntos al colaborador.
   */
  @Override
  public void efectuar() {
    this.colaborador.agregarColocacionHeladera(this);
  }

  /**
   * Metodo getMesesActiva que devuelve la cantidad de meses que la heladera lleva activa.
   */
  public Integer getMesesActiva() {
    return DateHelper.mesesEntre(this.heladera.getFechaPuestaFuncionamiento(), LocalDate.now());
  }

  /**
   * Metodo calcularPuntosMensuales que devuelve los puntos acumulados por la heladera cada mes.
   */
  public int calcularPuntosMensuales() {
    return getMesesActiva() * 5;
  }
}
