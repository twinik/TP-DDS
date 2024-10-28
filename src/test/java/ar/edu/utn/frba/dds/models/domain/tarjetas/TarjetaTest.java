package ar.edu.utn.frba.dds.models.domain.tarjetas;

import ar.edu.utn.frba.dds.helpers.GeneradorDeCodigosHelper;
import ar.edu.utn.frba.dds.models.domain.PersonaVulnerable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class TarjetaTest {

    private Tarjeta tarjeta;
    private PersonaVulnerable duenioMock;

    @BeforeEach
    public void setUp() {
        duenioMock = Mockito.mock(PersonaVulnerable.class);
        FrecuenciaUso frecuenciaDiaria = new FrecuenciaDiaria();
        FrecuenciaDiaria.setMaximosUsosBase(4);
        tarjeta = Tarjeta.of(GeneradorDeCodigosHelper.generarAlfanumericoUnico(11), 5, frecuenciaDiaria, duenioMock);
    }

    @Test
    @DisplayName("Test de inicialización correcta")
    public void testInicializacionCorrecta() {
        Assertions.assertEquals(5, tarjeta.getNroUsos());
        Assertions.assertInstanceOf(FrecuenciaDiaria.class, tarjeta.getFrecuenciaPermitida());
        Assertions.assertNotNull(tarjeta.getUsos());
        Assertions.assertEquals(0, tarjeta.getUsos().size());
        Assertions.assertEquals(duenioMock, tarjeta.getDuenio());
        Assertions.assertEquals(10, tarjeta.getCantidadUsosDia());
    }

    @Test
    @DisplayName("Test de permitir uso")
    public void testPermitirUso() {
        Mockito.when(duenioMock.cantidadMenores()).thenReturn(0);
        tarjeta.setCantidadUsosDia(0);
        Assertions.assertTrue(tarjeta.permiteUsar());

        tarjeta.setCantidadUsosDia(5);
        Assertions.assertFalse(tarjeta.permiteUsar());
    }
}