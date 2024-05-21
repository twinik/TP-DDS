package ar.edu.utn.frba.dds.domain.colaboraciones;

import ar.edu.utn.frba.dds.domain.colaboraciones.utils.FrecuenciaDonacion;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.*;

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
   *
   */
  private LocalDate fecha;

  /**
   * Default constructor
   */
  public DonacionDinero() {
  }


  @Override
  public void efectuar() {
    super.efectuar();
  }

}