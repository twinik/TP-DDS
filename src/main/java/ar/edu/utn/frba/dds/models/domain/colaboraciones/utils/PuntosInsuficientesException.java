package ar.edu.utn.frba.dds.models.domain.colaboraciones.utils;

/**
 * PuntosInsuficientesException class representa una excepcion de puntos insuficientes.
 */
public class PuntosInsuficientesException extends Exception {
  public PuntosInsuficientesException() {
    super("No puedes canjear este producto, ya que no cuentas con los puntos suficientes.\n");
  }
}
