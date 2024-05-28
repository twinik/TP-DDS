package ar.edu.utn.frba.dds.domain.heladeras;

/**
 * SensorMovimiento class permite representar un sensor de movimiento.
 */
public class SensorMovimiento {
  private Heladera heladera;

  public SensorMovimiento() {
  }

  public void alertarRobo() {
    // PONELE ?
    this.heladera.setActiva(false);
  }

}