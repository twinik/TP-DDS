package ar.edu.utn.frba.dds.domain.colaboraciones.utils;

public class PuntosInsuficientesException extends Exception {
  public PuntosInsuficientesException() {
    super("Este producto no puede ser canjeado, ya que el colaborador no cuenta con los puntos suficientes.\n");
  }
}
