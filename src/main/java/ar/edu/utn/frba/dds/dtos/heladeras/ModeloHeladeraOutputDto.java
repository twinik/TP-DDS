package ar.edu.utn.frba.dds.dtos.heladeras;

import ar.edu.utn.frba.dds.models.domain.heladeras.ModeloHeladera;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ModeloHeladeraOutputDto {
  private String id;
  private String modelo;
  private String rangoTemperaturas;

  public static ModeloHeladeraOutputDto fromModelo(ModeloHeladera modeloHeladera) {
    return new ModeloHeladeraOutputDto(modeloHeladera.getId(), modeloHeladera.getModelo(), getRangoTemperaturas(modeloHeladera));
  }

  private static String getRangoTemperaturas(ModeloHeladera modeloHeladera) {
    return String.format("[Min: %.1f ; Max: %.1f]", modeloHeladera.getTempMin(), modeloHeladera.getTempMax());
  }
}
