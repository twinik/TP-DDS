package ar.edu.utn.frba.dds.exceptions;

import lombok.Getter;

@Getter
public class AreaDeCoberturaVaciaException extends RuntimeException {

  Object formDto;

  public AreaDeCoberturaVaciaException(String message) {
    super(message);
  }

  public AreaDeCoberturaVaciaException(String message, Object formDto) {
    super(message);
    this.formDto = formDto;
  }
}
