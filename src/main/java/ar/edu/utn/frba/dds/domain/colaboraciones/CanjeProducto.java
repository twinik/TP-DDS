package ar.edu.utn.frba.dds.domain.colaboraciones;

import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;


@Getter
@AllArgsConstructor
public class CanjeProducto {
  Colaborador comprador;
  OfertaProducto ofertaCanjeada;

  LocalDateTime fechaCanje;
}
