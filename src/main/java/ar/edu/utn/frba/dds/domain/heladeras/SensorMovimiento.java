package ar.edu.utn.frba.dds.domain.heladeras;

import lombok.Getter;

/**
 * SensorMovimiento class permite representar un sensor de movimiento.
 */
@Getter
public class SensorMovimiento {
  private Heladera heladera;

  public SensorMovimiento() {
  }

  public void alertarRobo() {
    // PONELE ?
    this.heladera.setActiva(false);
  }

}