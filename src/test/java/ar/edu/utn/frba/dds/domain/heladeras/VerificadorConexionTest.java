package ar.edu.utn.frba.dds.domain.heladeras;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import ar.edu.utn.frba.dds.domain.pdfs.IPDFGeneratorAdapter;
import ar.edu.utn.frba.dds.domain.reportes.ReporteViandasPorColaborador;
import ar.edu.utn.frba.dds.repositories.IViandasRepository;
import ar.edu.utn.frba.dds.serviceLocator.ServiceLocator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class VerificadorConexionTest {

  Heladera unaHeladera;
  Heladera otraHeladera;

  @BeforeEach
  void initTests(){
    unaHeladera = new Heladera(LocalDate.now());
    unaHeladera.registrarTemperatura(10);
    unaHeladera.registrarTemperatura(20);
    otraHeladera = new Heladera(LocalDate.now());
    otraHeladera.getRegistroTemperaturas().add(new RegistroTemperatura(LocalDateTime.now().minusMinutes(6),20f));
  }

  @Test
  @DisplayName("No hubo falla en conexion")
  void verificarConexionEstable(){
    VerificadorConexionHeladera verificadorConexionHeladera = new VerificadorConexionHeladera();
    Assertions.assertFalse(verificadorConexionHeladera.huboFallaConexion(unaHeladera,5));
  }

  @Test
  @DisplayName("hubo falla en conexion")
  void verificarConexionFallida(){
    VerificadorConexionHeladera verificadorConexionHeladera = new VerificadorConexionHeladera();
    Assertions.assertTrue(verificadorConexionHeladera.huboFallaConexion(otraHeladera,5));
  }
}
