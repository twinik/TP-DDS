package ar.edu.utn.frba.dds.domain.colaboraciones;

import ar.edu.utn.frba.dds.domain.colaboraciones.calculadores.CalculadorDePuntosFactory;
import ar.edu.utn.frba.dds.domain.colaboraciones.calculadores.CalculadorPuntosDonacionVianda;
import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.domain.colaboradores.FormaColaboracion;
import ar.edu.utn.frba.dds.domain.heladeras.Vianda;
import java.time.LocalDate;

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
    this.calculadorDePuntos = CalculadorDePuntosFactory.create(FormaColaboracion.DONACION_VIANDA);
  }

  /**
   * Constructor con parametros.
   */
  public DonacionVianda(Vianda vianda, Colaborador colaborador) {
    super(colaborador, CalculadorDePuntosFactory.create(FormaColaboracion.DONACION_VIANDA), vianda.getFechaDonacion());
    this.vianda = vianda;
  }

  /**
   * Metodo efectuar que se encarga de sumar puntos al colaborador.
   */
  @Override
  public void efectuar() {
    super.efectuar();
  }

}