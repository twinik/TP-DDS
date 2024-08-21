package ar.edu.utn.frba.dds.domain.colaboraciones;

import ar.edu.utn.frba.dds.domain.PersonaVulnerable;
import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.domain.tarjetas.Tarjeta;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * AltaPersonaVulnerable class representa una colaboracion de un colaborador.
 * Consiste en dar de alta a una persona vulnerable.
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AltaPersonaVulnerable implements IPuntajeCalculable{
  private Long id;
  private Colaborador colaborador;
  private LocalDate fecha;
  private static final Float puntajePorAlta = 2f;
  private PersonaVulnerable persona;
  private Tarjeta tarjeta;

  /**
   * Constructor con parametros.
   */
  @Override
  public Float calcularPuntaje() {
    return puntajePorAlta;
  }

  /**
   * Metodo efectuar que se encarga de sumar puntos al colaborador.
   */

}
