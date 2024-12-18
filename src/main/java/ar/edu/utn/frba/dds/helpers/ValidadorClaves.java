package ar.edu.utn.frba.dds.helpers;

import ar.edu.utn.frba.dds.helpers.validaciones.Validacion;
import lombok.Getter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Representa el validador de las contrasenias.
 */
public class ValidadorClaves {
  private List<Validacion> validaciones;

  @Getter
  private MotivoNoValido motivoNoValida;

  public ValidadorClaves(List<Validacion> validaciones) {
    this.validaciones = validaciones;
  }

  public ValidadorClaves() {
    this.validaciones = new ArrayList<>();
  }

  public void agregarValidaciones(Validacion... validaciones) {
    Collections.addAll(this.validaciones, validaciones);
  }

  /**
   * Valida la contrasenia.
   */
  public boolean esValida(String contrasena) {
    for (Validacion v : validaciones) {
      if (!v.validar(contrasena)) {
        this.motivoNoValida = v.getMotivo();
        return false;
      }
    }
    return true;
  }


}