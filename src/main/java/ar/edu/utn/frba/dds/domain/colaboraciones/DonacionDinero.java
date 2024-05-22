package ar.edu.utn.frba.dds.domain.colaboraciones;

import ar.edu.utn.frba.dds.domain.colaboraciones.calculadores.CalculadorPuntosDonacionDinero;
import ar.edu.utn.frba.dds.domain.colaboraciones.utils.FrecuenciaDonacion;
import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

/**
 *
 */
@Getter
@AllArgsConstructor
public class DonacionDinero extends Colaboracion {
  /**
   *
   */
  private Float monto;

  /**
   *
   */
  private FrecuenciaDonacion frecuencia;

  /**
   * Default constructor
   */
  public DonacionDinero() {
    this.calculadorDePuntos = new CalculadorPuntosDonacionDinero();
  }

  public DonacionDinero(Colaborador colaborador, Float monto, FrecuenciaDonacion frecuencia, LocalDate fecha) {
    super(colaborador, new CalculadorPuntosDonacionDinero(), fecha);
    this.monto = monto;
    this.frecuencia = frecuencia;
  }

  @Override
  public void efectuar() {
    super.efectuar();
  }

}