package ar.edu.utn.frba.dds.domain.helpers.factories;

import ar.edu.utn.frba.dds.domain.helpers.ValidadorClaves;
import ar.edu.utn.frba.dds.domain.helpers.validaciones.Validacion;
import ar.edu.utn.frba.dds.domain.helpers.validaciones.ValidacionLargoClaves;
import ar.edu.utn.frba.dds.domain.helpers.validaciones.ValidacionListaClavesNuevo;
import java.util.ArrayList;
import java.util.List;

/**
 * ValidadorFactory interface permite crear un validador de claves.
 */
public class ValidadorFactory {

  /**
   *
   * @return un validador de claves configurado segun el estandar owasp
   */
  public static ValidadorClaves create() {
    List<Validacion> estrategias = new ArrayList<>();
    estrategias.add(new ValidacionLargoClaves());
    estrategias.add(new ValidacionListaClavesNuevo());
    return new ValidadorClaves(estrategias);
  }
}
