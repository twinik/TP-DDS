package ar.edu.utn.frba.dds.models.domain.excepciones;

public class NoAutorizadoParaAbrirHeladeraException extends RuntimeException {
  public NoAutorizadoParaAbrirHeladeraException(String message) {
    super(message);
  }
}
