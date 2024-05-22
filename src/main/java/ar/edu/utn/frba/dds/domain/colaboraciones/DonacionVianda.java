package ar.edu.utn.frba.dds.domain.colaboraciones;

import ar.edu.utn.frba.dds.domain.colaboraciones.calculadores.CalculadorPuntosDonacionVianda;
import ar.edu.utn.frba.dds.domain.heladeras.Vianda;
import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;

/**
 *
 */
public class DonacionVianda extends Colaboracion{

  private Vianda vianda;

  public DonacionVianda() {
    this.calculadorDePuntos = new CalculadorPuntosDonacionVianda();
  }
  public DonacionVianda(Vianda vianda) {
    this.calculadorDePuntos = new CalculadorPuntosDonacionVianda();
    this.vianda = vianda;
    this.fecha = vianda.getFechaDonacion();
  }

  @Override
  public void efectuar() {
    super.efectuar();
  }

}