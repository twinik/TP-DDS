package ar.edu.utn.frba.dds.domain.colaboraciones;

import ar.edu.utn.frba.dds.domain.colaboraciones.calculadores.CalculadorDePuntosFactory;
import ar.edu.utn.frba.dds.domain.colaboraciones.calculadores.CalculadorPuntosDonacionDinero;
import ar.edu.utn.frba.dds.domain.colaboraciones.calculadores.CalculadorPuntosDonacionVianda;
import ar.edu.utn.frba.dds.domain.colaboraciones.utils.FrecuenciaDonacion;
import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import java.time.LocalDate;
import ar.edu.utn.frba.dds.domain.colaboradores.FormaColaboracion;
import lombok.AllArgsConstructor;
import lombok.Getter;



/**
 * DonacionDinero class representa una colaboracion de un colaborador.
 * Consiste en donar dinero.
 */
@Getter
@AllArgsConstructor
public class DonacionDinero extends Colaboracion {
  private Float monto;
  private FrecuenciaDonacion frecuencia;

  /**
   * Constructor por defecto.
   */
  public DonacionDinero(CalculadorPuntosDonacionDinero calculador) {
    this.calculadorDePuntos = calculador;
  }

  /**
   * Constructor con parametros.
   */
  public DonacionDinero(Colaborador colaborador,
                        Float monto, FrecuenciaDonacion frecuencia, LocalDate fecha, CalculadorPuntosDonacionDinero calculador) {
    super(colaborador, calculador, fecha);
    this.monto = monto;
    this.frecuencia = frecuencia;
  }

  /**
   * Metodo efectuar que se encarga de sumar puntos al colaborador.
   */
  @Override
  public void efectuar() {
    super.efectuar();
  }

}