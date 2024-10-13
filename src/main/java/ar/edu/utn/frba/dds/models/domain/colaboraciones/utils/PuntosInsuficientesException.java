package ar.edu.utn.frba.dds.models.domain.colaboraciones.utils;

/**
 * PuntosInsuficientesException class representa una excepcion de puntos insuficientes.
 */
public class PuntosInsuficientesException extends Exception {
  public PuntosInsuficientesException(String producto) {
    super("Puntos insuficientes para canjear el producto: " + producto);
  }
}
