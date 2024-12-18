package ar.edu.utn.frba.dds.models.domain.helpers;

import ar.edu.utn.frba.dds.helpers.ValidadorClaves;
import ar.edu.utn.frba.dds.helpers.factories.ValidadorFactory;
import ar.edu.utn.frba.dds.helpers.validaciones.ValidacionLargoClaves;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ValidadorTest {

  ValidadorClaves unValidador;

  @BeforeEach
  void test_init() {
    unValidador = ValidadorFactory.create();
  }

  @Test
  @DisplayName("contrasena correcta")
  void validarContrasenaCorrecta() {
    Assertions.assertTrue(unValidador.esValida("thomas_ariel_luca"));
  }

  @Test
  @DisplayName("contrasena entre las 10.000 peores")
  void validarContrasenaEnLista10000() {
    Assertions.assertFalse(unValidador.esValida("password"));
    Assertions.assertEquals("La clave aparece en la lista de las 10.000 peores claves", unValidador.getMotivoNoValida().getMotivo());
  }

  @Test
  @DisplayName("contrasena muy corta")
  void validarContrasenaCorta() {
    Assertions.assertFalse(unValidador.esValida("123"));
    Assertions.assertEquals("La contraseña es muy corta, debe tener como minimo " + ValidacionLargoClaves.getLONGITUD_MINIMA() + " caracteres.", unValidador.getMotivoNoValida().getMotivo());
  }

  @Test
  @DisplayName("contrasena muy larga")
  void validarContrasenaLarga() {
    Assertions.assertFalse(unValidador.esValida("D$w9&XqPz4!sGv2@rN#lJ*cEoF+tH(3zL-xM/vK,nA.bQ1:wG^fR%yT&uI=8Y7U6iO;9P0"));
    Assertions.assertEquals("La contraseña es muy larga, debe tener como maximo " + ValidacionLargoClaves.getLONGITUD_MAXIMA() + " caracteres.", unValidador.getMotivoNoValida().getMotivo());
  }
}