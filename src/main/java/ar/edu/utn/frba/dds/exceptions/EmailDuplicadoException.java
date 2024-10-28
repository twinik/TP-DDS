package ar.edu.utn.frba.dds.exceptions;

import lombok.Getter;

@Getter
public class EmailDuplicadoException extends RuntimeException {

  Object formDto;


  public EmailDuplicadoException(String message) {
    super(message);
  }

  public EmailDuplicadoException(String message, Object formDto) {
    super(message);
    this.formDto = formDto;
  }
}
