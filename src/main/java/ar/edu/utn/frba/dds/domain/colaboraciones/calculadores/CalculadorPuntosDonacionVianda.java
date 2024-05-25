package ar.edu.utn.frba.dds.domain.colaboraciones.calculadores;

import ar.edu.utn.frba.dds.domain.colaboraciones.Colaboracion;

/**
 * CalculadorPuntosDonacionVianda class permite calcular los puntos de una colaboracion de donacion de vianda.
 */
public class CalculadorPuntosDonacionVianda implements CalculadorDePuntos {
  public Float calcularPuntos(Colaboracion colaboracion) {
    // suma 1 por cada vianda distribuida
    return (float) 1.5;
  }
}
