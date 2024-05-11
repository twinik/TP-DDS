package ar.edu.utn.frba.dds.domain.colaboraciones;

import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;


@Getter
public class CanjeProducto {
  Colaborador comprador;
  OfertaProducto ofertaCanjeada;
  LocalDateTime fechaCanje;

  public CanjeProducto(Colaborador comprador, OfertaProducto ofertaCanjeada, LocalDateTime fechaCanje) {
    this.comprador = comprador;
    this.ofertaCanjeada = ofertaCanjeada;
    this.fechaCanje = fechaCanje;
    // el chequeo de si tiene los puntos necesarios deberia hacerse antes. Si no se puede agregar aca y que tire una exception
    comprador.restarPuntos(ofertaCanjeada.getPuntosNecesarios());
  }
}
