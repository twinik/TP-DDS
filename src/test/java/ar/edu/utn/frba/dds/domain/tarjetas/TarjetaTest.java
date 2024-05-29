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
    FrecuenciaDiaria.setMaximosUsosBase(4);
    tarjeta = new Tarjeta(5, frecuenciaDiaria, duenioMock, new Date(), 10);
  }

  @Test
  @DisplayName("Test de inicialización correcta")
  public void testInicializacionCorrecta() {
    assertEquals(5, tarjeta.getNroUsos());
    assertInstanceOf(FrecuenciaDiaria.class, tarjeta.getFrecuenciaPermitida());
    assertNotNull(tarjeta.getUsos());
    assertEquals(0, tarjeta.getUsos().size());
    assertEquals(duenioMock, tarjeta.getDuenio());
    assertEquals(0, tarjeta.getCantidadUsosDia());
  }

  @Test
  @DisplayName("Test de permitir uso")
  public void testPermitirUso() {
    Mockito.when(duenioMock.cantidadMenores()).thenReturn(0);
    tarjeta.setCantidadUsosDia(0);
    assertTrue(tarjeta.permiteUsar());

    tarjeta.setCantidadUsosDia(5);
    assertFalse(tarjeta.permiteUsar());
  }
}