package ar.edu.utn.frba.dds.domain.colaboraciones;

import ar.edu.utn.frba.dds.domain.heladeras.Vianda;
import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;

/**
 *
 */
public class DonacionVianda extends Colaboracion{

  /**
   * Default constructor
   */

  public DonacionVianda() {}
  public DonacionVianda(Vianda vianda) {
    this.vianda = vianda;
  }

  @Override
  public void efectuar() {
    super.efectuar();
  }

  /**
   *
   */

  /**
   *
   */
  private Vianda vianda;

}