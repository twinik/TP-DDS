package ar.edu.utn.frba.dds.domain.colaboraciones.calculadores;

import ar.edu.utn.frba.dds.domain.colaboraciones.Colaboracion;
import ar.edu.utn.frba.dds.domain.colaboraciones.RedistribucionViandas;

/**
 * CalculadorPuntosRedistribucionVianda class permite calcular los puntos de una colaboracion de redistribucion de viandas.
 */
public class CalculadorPuntosRedistribucionVianda implements CalculadorDePuntos {
  public Float calcularPuntos(Colaboracion colaboracion) {
    RedistribucionViandas redistribucion = (RedistribucionViandas) colaboracion;
    return (float) redistribucion.getCantidad();
  }
}
