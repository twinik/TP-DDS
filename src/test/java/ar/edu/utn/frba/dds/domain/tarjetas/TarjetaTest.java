package ar.edu.utn.frba.dds.domain.tarjetas;

import ar.edu.utn.frba.dds.domain.PersonaVulnerable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.quartz.JobExecutionContext;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class TarjetaTest {

  private Tarjeta tarjeta;
  private PersonaVulnerable duenioMock;

  @BeforeEach
  public void setUp() {
    duenioMock = Mockito.mock(PersonaVulnerable.class);
    FrecuenciaUso frecuenciaDiaria = new FrecuenciaDiaria();
    FrecuenciaDiaria.setMaximosUsosBase(5);
    tarjeta = new Tarjeta("123", 5, frecuenciaDiaria, duenioMock, new Date(), 10);
  }

  @Test
  @DisplayName("Test de inicialización correcta")
  public void testInicializacionCorrecta() {
    assertEquals("123", tarjeta.getCodigo());
    assertEquals(5, tarjeta.getNroUsos());
    assertInstanceOf(FrecuenciaDiaria.class, tarjeta.getFrecuenciaPermitida());
    assertNotNull(tarjeta.getUsos());
    assertEquals(0, tarjeta.getUsos().size());
    assertEquals(duenioMock, tarjeta.getDuenio());
    assertEquals(0, tarjeta.getCantidadUsosDia());
  }

  @Test
  @DisplayName("Test de reseteo de usos diarios")
  public void testResetarUsosDia() {
    tarjeta.setCantidadUsosDia(5);
    Tarjeta.ResetarUsosDia job = tarjeta.new ResetarUsosDia();
    JobExecutionContext contextMock = Mockito.mock(JobExecutionContext.class);
    job.execute(contextMock);
    assertEquals(0, tarjeta.getCantidadUsosDia());
  }

  @Test
  @DisplayName("Test de permitir uso")
  public void testPermitirUso() {
    Mockito.when(duenioMock.cantidadMenores()).thenReturn(0);
    tarjeta.setCantidadUsosDia(0);
    assertTrue(tarjeta.getFrecuenciaPermitida().permiteUsar(tarjeta));

    tarjeta.setCantidadUsosDia(5);
    assertFalse(tarjeta.getFrecuenciaPermitida().permiteUsar(tarjeta));
  }
}