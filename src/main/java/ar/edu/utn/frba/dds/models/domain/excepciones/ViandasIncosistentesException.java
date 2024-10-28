package ar.edu.utn.frba.dds.models.domain.excepciones;

public class ViandasIncosistentesException extends RuntimeException {
  public ViandasIncosistentesException() {
  }

  public ViandasIncosistentesException(String message) {
    super(message);
  }
}
