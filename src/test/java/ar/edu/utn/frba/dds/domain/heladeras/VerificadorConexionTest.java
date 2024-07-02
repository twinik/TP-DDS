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
    otraHeladera.getRegistroTemperaturas().add(new RegistroTemperatura(LocalDateTime.of(LocalDate.of(2024,7,2), LocalTime.of(3,20)),50));
  }

  @Test
  @DisplayName("No hubo falla en conexion")
  void verificarConexionEstable(){
    VerificadorConexionHeladera verificadorConexionHeladera = new VerificadorConexionHeladera(unaHeladera,5);
    Assertions.assertFalse(verificadorConexionHeladera.huboFallaConexion());
  }

  @Test
  @DisplayName("hubo falla en conexion")
  void verificarConexionFallida(){
    VerificadorConexionHeladera verificadorConexionHeladera = new VerificadorConexionHeladera(otraHeladera,5);
    Assertions.assertTrue(verificadorConexionHeladera.huboFallaConexion());
  }
}
