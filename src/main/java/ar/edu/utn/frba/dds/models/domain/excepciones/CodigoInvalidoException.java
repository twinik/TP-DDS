package ar.edu.utn.frba.dds.models.domain.excepciones;

import lombok.Getter;

@Getter
public class CodigoInvalidoException extends RuntimeException {

  Object formDto;

  public CodigoInvalidoException(String message) {
    super(message);
  }

  public CodigoInvalidoException(String message, Object formDto) {
    super(message);
    this.formDto = formDto;
  }
}
