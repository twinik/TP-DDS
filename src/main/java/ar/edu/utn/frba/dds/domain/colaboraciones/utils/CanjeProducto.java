package ar.edu.utn.frba.dds.domain.colaboraciones.utils;

import ar.edu.utn.frba.dds.domain.colaboraciones.OfertaProducto;
import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import java.time.LocalDateTime;
import lombok.Getter;

/**
 * CanjeProducto class representa una colaboracion de un colaborador.
 * Consiste en canjear un producto.
 */
@Getter
public class CanjeProducto {
  Colaborador comprador;
  OfertaProducto ofertaCanjeada;
  LocalDateTime fechaCanje;

  /**
   * Constructor con parametros.
   */
  public CanjeProducto(Colaborador comprador,
                       OfertaProducto ofertaCanjeada,
                       LocalDateTime fechaCanje) throws PuntosInsuficientesException {
    this.comprador = comprador;
    this.ofertaCanjeada = ofertaCanjeada;
    this.fechaCanje = fechaCanje;
    if (!ofertaCanjeada.puedeSerCanjeadoPor(comprador)) {
      throw new PuntosInsuficientesException();
    }
    comprador.restarPuntos(ofertaCanjeada.getPuntosNecesarios());
  }
}
