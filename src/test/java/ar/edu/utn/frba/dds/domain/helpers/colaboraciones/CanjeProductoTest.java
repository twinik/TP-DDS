package ar.edu.utn.frba.dds.domain.helpers.colaboraciones;

import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.domain.colaboraciones.CanjeProducto;
import ar.edu.utn.frba.dds.domain.colaboraciones.OfertaProducto;
import ar.edu.utn.frba.dds.domain.colaboraciones.PuntosInsuficientesException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CanjeProductoTest {
  private static final LocalDateTime FECHA_CANJE = LocalDateTime.now();
  private static final float PUNTOS_NECESARIOS = 100.0f;

  private Colaborador comprador;
  private OfertaProducto ofertaCanjeada;
  private CanjeProducto canjeProducto;

  @BeforeEach
  void setUp() {
    comprador = Mockito.mock(Colaborador.class);
    ofertaCanjeada = Mockito.mock(OfertaProducto.class);
  }

  @Test
  @DisplayName("Canje producto exitoso")
  void testCanjeProductoExitoso() throws PuntosInsuficientesException {
    when(ofertaCanjeada.puedeSerCanjeadoPor(comprador)).thenReturn(true);
    when(ofertaCanjeada.getPuntosNecesarios()).thenReturn(PUNTOS_NECESARIOS);

    canjeProducto = new CanjeProducto(comprador, ofertaCanjeada, FECHA_CANJE);

    assertEquals(comprador, canjeProducto.getComprador());
    assertEquals(ofertaCanjeada, canjeProducto.getOfertaCanjeada());
    assertEquals(FECHA_CANJE, canjeProducto.getFechaCanje());
    verify(comprador).restarPuntos(PUNTOS_NECESARIOS);
  }

  @Test
  @DisplayName("Canje producto con puntos insuficientes")
  void testCanjeProductoConPuntosInsuficientes() {
    when(ofertaCanjeada.puedeSerCanjeadoPor(comprador)).thenReturn(false);

    assertThrows(PuntosInsuficientesException.class, () ->
        new CanjeProducto(comprador, ofertaCanjeada, FECHA_CANJE)
    );

    verify(comprador, never()).restarPuntos(anyFloat());
  }
}
