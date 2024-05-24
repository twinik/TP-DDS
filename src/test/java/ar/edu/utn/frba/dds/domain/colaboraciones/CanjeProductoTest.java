package ar.edu.utn.frba.dds.domain.colaboraciones;

import ar.edu.utn.frba.dds.domain.colaboraciones.utils.CategoriaOferta;
import ar.edu.utn.frba.dds.domain.colaboraciones.utils.Producto;
import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.domain.colaboraciones.utils.CanjeProducto;
import ar.edu.utn.frba.dds.domain.colaboraciones.OfertaProducto;
import ar.edu.utn.frba.dds.domain.colaboraciones.utils.PuntosInsuficientesException;
import ar.edu.utn.frba.dds.domain.colaboradores.TipoColaborador;
import ar.edu.utn.frba.dds.domain.colaboradores.TipoPersona;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CanjeProductoTest {

  private static final LocalDateTime FECHA_CANJE = LocalDateTime.now();
  private static final float PUNTOS_NECESARIOS = 100.0f;

  private Colaborador comprador;
  private OfertaProducto ofertaCanjeada;

  @BeforeEach
  void setUp() {
    comprador = new Colaborador();
    comprador.setTipoColaborador(new TipoColaborador(TipoPersona.PERSONA_HUMANA, null));
    comprador.setPuntosGanados((float) 0);
    ofertaCanjeada = new OfertaProducto(new Producto(), PUNTOS_NECESARIOS, CategoriaOferta.ELECTRONICA);
  }

  @Test
  @DisplayName("Canje de producto exitoso")
  void testCanjeProductoExitoso() throws PuntosInsuficientesException {
    comprador.sumarPuntos(PUNTOS_NECESARIOS);
    CanjeProducto canjeProducto = new CanjeProducto(comprador, ofertaCanjeada, FECHA_CANJE);

    assertEquals(0, comprador.getPuntosGanados());
  }

  @Test
  @DisplayName("Canje de producto fallido por puntos insuficientes")
  void testCanjeProductoConPuntosInsuficientes() {
    comprador.sumarPuntos(PUNTOS_NECESARIOS - 1);

    assertThrows(PuntosInsuficientesException.class, () ->
        new CanjeProducto(comprador, ofertaCanjeada, FECHA_CANJE)
    );
    assertEquals(PUNTOS_NECESARIOS - 1, comprador.getPuntosGanados());
  }
}
