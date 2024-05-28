package ar.edu.utn.frba.dds.domain.heladeras;

/**
 * SensorMovimiento class permite representar un sensor de movimiento.
 */
public class SensorMovimiento {
  /**
   * Default constructor.
   */

  private Heladera heladera;
  public SensorMovimiento() {
  }

  public void alertarRobo(Heladera heladera) {
    // PONELE ?
    this.heladera.setActiva(false);
  }

}