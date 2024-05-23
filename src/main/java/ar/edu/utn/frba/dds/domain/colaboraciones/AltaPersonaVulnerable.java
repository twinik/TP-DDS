package ar.edu.utn.frba.dds.domain.colaboraciones;

import ar.edu.utn.frba.dds.domain.PersonaVulnerable;
import ar.edu.utn.frba.dds.domain.colaboraciones.calculadores.CalculadorPuntosAltaPersona;
import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
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
                               LocalDate fecha) {
    super(colaborador, new CalculadorPuntosAltaPersona(), fecha);
    this.persona = persona;
    this.tarjeta = tarjeta;
  }

  /**
   * Constructor por defecto.
   */
  public AltaPersonaVulnerable() {
    this.calculadorDePuntos = new CalculadorPuntosAltaPersona();
  }

  /**
   * Metodo efectuar que se encarga de sumar puntos al colaborador.
   */
  @Override
  public void efectuar() {
    super.efectuar();
  }

}
