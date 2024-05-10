package ar.edu.utn.frba.dds.domain.heladeras;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class SensorTemperatura {


  private Float utlimaTempRegistrada;

  public void registrarTemperatura(Float temp) {
    this.utlimaTempRegistrada = temp;
  }

}