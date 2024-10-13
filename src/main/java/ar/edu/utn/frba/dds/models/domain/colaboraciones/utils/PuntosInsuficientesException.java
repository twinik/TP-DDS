package ar.edu.utn.frba.dds.models.domain.colaboraciones.utils;

import ar.edu.utn.frba.dds.models.messageFactory.MensajePuntosInsuficientesFactory;

/**
 * PuntosInsuficientesException class representa una excepcion de puntos insuficientes.
 */
public class PuntosInsuficientesException extends Exception {
  public PuntosInsuficientesException(String producto) {
    super(MensajePuntosInsuficientesFactory.generarMensaje(producto));
  }
}
