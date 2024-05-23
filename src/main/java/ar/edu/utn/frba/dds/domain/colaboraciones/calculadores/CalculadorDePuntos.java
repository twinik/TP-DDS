package ar.edu.utn.frba.dds.domain.colaboraciones.calculadores;


import ar.edu.utn.frba.dds.domain.colaboraciones.Colaboracion;

/**
 * CalculadorDePuntos interface permite calcular los puntos de una colaboracion.
 */
public interface CalculadorDePuntos {
  Float calcularPuntos(Colaboracion colaboracion);
}
