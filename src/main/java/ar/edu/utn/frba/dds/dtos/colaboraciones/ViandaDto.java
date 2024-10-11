package ar.edu.utn.frba.dds.dtos.colaboraciones;

import ar.edu.utn.frba.dds.dtos.heladeras.HeladeraDto;
import io.javalin.http.Context;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ViandaDto {
  private String desc;
  private String fechaCaducidad;

  private String fechaDonacion;
  private Integer calorias;
  private Float peso;
  private HeladeraDto heladeraDto;
  private String idColaoborador;

  public static ViandaDto of(Context context){
    return ViandaDto.builder().desc()
  }

}
