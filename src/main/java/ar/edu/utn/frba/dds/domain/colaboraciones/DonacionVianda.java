package ar.edu.utn.frba.dds.domain.colaboraciones;

import ar.edu.utn.frba.dds.domain.colaboraciones.calculadores.CalculadorPuntosDonacionVianda;
import ar.edu.utn.frba.dds.domain.heladeras.Vianda;

/**
 * DonacionVianda class representa una colaboracion de un colaborador.
 * Consiste en donar viandas.
 */
public class DonacionVianda extends Colaboracion {
  private Vianda vianda;

  /**
   * Constructor por defecto.
   */
  public DonacionVianda() {
    this.calculadorDePuntos = new CalculadorPuntosDonacionVianda();
  }

  /**
   * Constructor con parametros.
   */
  public DonacionVianda(Vianda vianda) {
    this.calculadorDePuntos = new CalculadorPuntosDonacionVianda();
    this.vianda = vianda;
    this.fecha = vianda.getFechaDonacion();
  }

  /**
   * Metodo efectuar que se encarga de sumar puntos al colaborador.
   */
  @Override
  public void efectuar() {
    super.efectuar();
  }

}