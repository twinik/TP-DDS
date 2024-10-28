package ar.edu.utn.frba.dds.dtos.colaboraciones;

import ar.edu.utn.frba.dds.models.domain.recomendadorPuntosColocacion.PuntoColocacion;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Builder
public class RecomendacionColocacionDto {
  Float latitud;
  Float longitud;
  String nombre;
  String calle;
  Integer altura;
  private List<Float> coords;

  public static RecomendacionColocacionDto fromPunto(PuntoColocacion punto) {
    return RecomendacionColocacionDto.builder().latitud(punto.getUbicacion().getLatitud())
        .longitud(punto.getUbicacion().getLongitud())
        .coords(List.of(punto.getUbicacion().getLatitud(), punto.getUbicacion().getLongitud()))
        .nombre(punto.getNombre())
        .calle(punto.getDireccion().getCalle())
        .altura(punto.getDireccion().getAltura()).build();
  }
}
