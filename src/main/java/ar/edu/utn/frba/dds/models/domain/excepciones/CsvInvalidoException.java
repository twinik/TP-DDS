package ar.edu.utn.frba.dds.models.domain.excepciones;

public class CsvInvalidoException extends RuntimeException {
  public CsvInvalidoException(String msg) {
    super(msg);
  }
}
