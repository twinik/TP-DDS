package ar.edu.utn.frba.dds.models.domain.heladeras;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class VerificadorConexionTest {

  Heladera unaHeladera;
  Heladera otraHeladera;

  @BeforeEach
  void initTests() {
    unaHeladera = new Heladera(LocalDate.now());
    unaHeladera.registrarTemperatura(10);
    unaHeladera.registrarTemperatura(20);
    otraHeladera = new Heladera(LocalDate.now());
    otraHeladera.getRegistroTemperaturas().add(new RegistroTemperatura(LocalDateTime.now().minusMinutes(6), 20f));
  }

  @Test
  @DisplayName("No hubo falla en conexion")
  void verificarConexionEstable() {
    VerificadorConexionHeladera verificadorConexionHeladera = new VerificadorConexionHeladera();
    Assertions.assertFalse(verificadorConexionHeladera.huboFallaConexion(unaHeladera, 5));
  }

  @Test
  @DisplayName("hubo falla en conexion")
  void verificarConexionFallida() {
    VerificadorConexionHeladera verificadorConexionHeladera = new VerificadorConexionHeladera();
    Assertions.assertTrue(verificadorConexionHeladera.huboFallaConexion(otraHeladera, 5));
  }
}
