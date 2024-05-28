package ar.edu.utn.frba.dds.domain.heladeras;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Clase: Modelo Heladera, cada heladera viene definida con estos parametros
 */
@Getter
@AllArgsConstructor
public class ModeloHeladera {
  private String modelo;
  private float tempMin;
  private float tempMax;
}
