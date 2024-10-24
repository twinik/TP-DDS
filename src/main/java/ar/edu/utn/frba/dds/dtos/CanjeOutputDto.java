package ar.edu.utn.frba.dds.dtos;

import ar.edu.utn.frba.dds.models.domain.colaboraciones.utils.CanjeProducto;
import lombok.Builder;
import lombok.Getter;

import io.javalin.http.Context;
import java.time.format.DateTimeFormatter;

@Builder
@Getter
public class CanjeOutputDto {
  private String nombreProducto;
  private String urlFoto;
  private String fechaCanje;
  private Float puntosGastados;

  public static CanjeOutputDto fromCanje(CanjeProducto canjeProducto) {
    return CanjeOutputDto.builder().nombreProducto(canjeProducto.getOfertaCanjeada().getProducto().getNombre())
        .urlFoto(canjeProducto.getOfertaCanjeada().getProducto().getUrlFoto())
        .fechaCanje(canjeProducto.getFechaCanje().format(DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm")))
        .puntosGastados(canjeProducto.getPuntosGastados())
        .build();
  }

}
