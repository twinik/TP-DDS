package ar.edu.utn.frba.dds.domain.colaboraciones;

import ar.edu.utn.frba.dds.domain.PersonaVulnerable;
import ar.edu.utn.frba.dds.domain.colaboraciones.calculadores.CalculadorDePuntosFactory;
import ar.edu.utn.frba.dds.domain.colaboraciones.calculadores.CalculadorPuntosAltaPersona;
import ar.edu.utn.frba.dds.domain.colaboraciones.calculadores.CalculadorPuntosDonacionDinero;
import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.domain.colaboradores.FormaColaboracion;
import ar.edu.utn.frba.dds.domain.tarjetas.Tarjeta;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

/**
 * AltaPersonaVulnerable class representa una colaboracion de un colaborador.
 * Consiste en dar de alta a una persona vulnerable.
 */

@Getter
@Setter
public class AltaPersonaVulnerable extends Colaboracion {
  private PersonaVulnerable persona;
  private Tarjeta tarjeta;

  /**
   * Constructor con parametros.
   */
  public AltaPersonaVulnerable(Colaborador colaborador,
                               PersonaVulnerable persona,
                               Tarjeta tarjeta,
                               LocalDate fecha, CalculadorPuntosAltaPersona calculador) {
    super(colaborador, calculador, fecha);
    this.persona = persona;
    this.tarjeta = tarjeta;
  }

  /**
   * Constructor por defecto.
   */
  public AltaPersonaVulnerable(CalculadorPuntosAltaPersona calculador) {
    this.calculadorDePuntos = calculador;
  }

  /**
   * Metodo efectuar que se encarga de sumar puntos al colaborador.
   */
  @Override
  public void efectuar() {
    super.efectuar();
  }

}
