package ar.edu.utn.frba.dds.domain.excepciones;

public class CsvInvalidoException extends RuntimeException {
  public CsvInvalidoException(String msg) {
    super(msg);
  }
}
