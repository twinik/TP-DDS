package ar.edu.utn.frba.dds.exceptions;

public class EmailDuplicadoException extends RuntimeException{
  public EmailDuplicadoException(String message) {
    super(message);
  }
}
