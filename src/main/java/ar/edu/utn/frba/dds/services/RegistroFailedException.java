package ar.edu.utn.frba.dds.services;

public class RegistroFailedException extends RuntimeException{
  public RegistroFailedException(String message) {
    super(message);
  }
}
