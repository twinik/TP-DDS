package ar.edu.utn.frba.dds.domain.colaboraciones;

import ar.edu.utn.frba.dds.domain.colaboraciones.calculadores.CalculadorDePuntosFactory;
import ar.edu.utn.frba.dds.domain.colaboraciones.calculadores.CalculadorPuntosDonacionDinero;
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
  public DonacionDinero() {
    this.calculadorDePuntos = CalculadorDePuntosFactory.create(FormaColaboracion.DONACION_DINERO);
  }

  /**
   * Constructor con parametros.
   */
  public DonacionDinero(Colaborador colaborador,
                        Float monto, FrecuenciaDonacion frecuencia, LocalDate fecha) {
    super(colaborador, CalculadorDePuntosFactory.create(FormaColaboracion.DONACION_DINERO), fecha);
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