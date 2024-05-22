package ar.edu.utn.frba.dds.domain.colaboraciones;

import ar.edu.utn.frba.dds.domain.colaboraciones.calculadores.CalculadorDePuntos;
import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

/**
 *
 */
@Getter
@Setter
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

  protected CalculadorDePuntos calculadorDePuntos;

  protected LocalDate fecha;

  /**
   *
   */
  public void efectuar() {
    this.colaborador.sumarPuntos(this.calculadorDePuntos.calcularPuntos(this));
  }


}