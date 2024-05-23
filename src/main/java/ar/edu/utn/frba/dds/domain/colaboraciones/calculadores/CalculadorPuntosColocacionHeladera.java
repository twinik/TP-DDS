package ar.edu.utn.frba.dds.domain.colaboraciones.calculadores;

import ar.edu.utn.frba.dds.domain.colaboraciones.Colaboracion;
import ar.edu.utn.frba.dds.domain.colaboraciones.ColocacionHeladeras;

/**
 * CalculadorPuntosColocacionHeladera class permite calcular los puntos de una colaboracion de colocacion de heladera.
 */
public class CalculadorPuntosColocacionHeladera implements CalculadorDePuntos {
  public Float calcularPuntos(Colaboracion colaboracion) {
    ColocacionHeladeras colocacionHeladera = (ColocacionHeladeras) colaboracion;
    return 5.0f * colocacionHeladera.getMesesActiva();
  }
}
