package ar.edu.utn.frba.dds.domain.colaboraciones.calculadores;

import ar.edu.utn.frba.dds.domain.colaboraciones.Colaboracion;
import ar.edu.utn.frba.dds.domain.colaboraciones.ColocacionHeladeras;

public class CalculadorPuntosColocacionHeladera implements CalculadorDePuntos {
  public Float calcularPuntos(Colaboracion colaboracion) {
    ColocacionHeladeras colocacion = (ColocacionHeladeras) colaboracion;
    //TODO
    // [CANTIDAD_HELADERAS_ACTIVAS] * [sumatoria MESES_ACTIVAS] * 5
    // esto no se puede calcular al momento de colocar la heladera por lo de los meses activa???
    return (float) 0;
  }
}
