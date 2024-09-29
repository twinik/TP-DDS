package ar.edu.utn.frba.dds.services;

public class NoAutorizadoException extends RuntimeException{
  public NoAutorizadoException(String message) {
    super(message);
  }
}
