package ar.edu.utn.frba.dds.dtos.colaboraciones;

import ar.edu.utn.frba.dds.models.domain.colaboraciones.utils.FrecuenciaDonacion;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class FrecuenciaDonacionDineroDto {
  private Integer valor;
  private String nombre;

  public static FrecuenciaDonacionDineroDto of(FrecuenciaDonacion f){
    return new FrecuenciaDonacionDineroDto(f.ordinal(),FrecuenciaDonacion.mapearTexto(f));
  }

}
