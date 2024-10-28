package ar.edu.utn.frba.dds.exceptions;

import lombok.Getter;

@Getter
public class FormularioNoCompletadoException extends RuntimeException {
  private String idColaborador;

  public FormularioNoCompletadoException(String idColaborador) {
    super("El Colaborador debe contestar el formulario");
    this.idColaborador = idColaborador;
  }
}
