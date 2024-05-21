package ar.edu.utn.frba.dds.domain.colaboraciones;

import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import lombok.AllArgsConstructor;
import lombok.Getter;

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
  private Date fecha;

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