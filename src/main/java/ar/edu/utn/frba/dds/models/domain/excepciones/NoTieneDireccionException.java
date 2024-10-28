package ar.edu.utn.frba.dds.models.domain.excepciones;

import lombok.Getter;

@Getter
public class NoTieneDireccionException extends RuntimeException {
  private Object formDto;

  public NoTieneDireccionException(String message, Object formDto) {
    super(message);
    this.formDto = formDto;
  }
}
