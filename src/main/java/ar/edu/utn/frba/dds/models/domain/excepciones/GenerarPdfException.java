package ar.edu.utn.frba.dds.models.domain.excepciones;

public class GenerarPdfException extends RuntimeException {
  public GenerarPdfException(Exception e) {
    super(e);
  }
}
