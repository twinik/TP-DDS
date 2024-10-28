package ar.edu.utn.frba.dds.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ClaveNoCoincidenException extends RuntimeException {
  Object formDto;

  public ClaveNoCoincidenException(Object formDto) {
    super("Las claves ingresadas no coinciden!");
    this.formDto = formDto;
  }
}
