package ar.edu.utn.frba.dds.domain.colaboraciones.calculadores;

import ar.edu.utn.frba.dds.domain.colaboraciones.Colaboracion;

/**
 * CalculadorPuntosAltaPersona class permite calcular los puntos de una colaboracion de alta persona.
 */
public class CalculadorPuntosAltaPersona implements CalculadorDePuntos {
  public Float calcularPuntos(Colaboracion colaboracion) {
    return (float) 2;
  }
}
