package ar.edu.utn.frba.dds.domain.colaboraciones;

import ar.edu.utn.frba.dds.domain.colaboraciones.calculadores.CalculadorDePuntos;
import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 */
@Getter
@AllArgsConstructor
public abstract class Colaboracion {

  /**
   * Default constructor
   */
  public Colaboracion() {
  }

  /**
   *
   */
  protected Colaborador colaborador;

  private CalculadorDePuntos calculadorDePuntos;

  /**
   *
   */
  public abstract void efectuar();

}