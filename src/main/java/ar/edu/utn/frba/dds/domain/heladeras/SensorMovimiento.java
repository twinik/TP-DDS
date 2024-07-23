package ar.edu.utn.frba.dds.domain.heladeras;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * SensorMovimiento class permite representar un sensor de movimiento.
 */
@Getter
@Setter
@NoArgsConstructor
public class SensorMovimiento {
  private long id;
  private Heladera heladera;
  public void alertarRobo() {
    this.heladera.inhabilitar();
  }

}