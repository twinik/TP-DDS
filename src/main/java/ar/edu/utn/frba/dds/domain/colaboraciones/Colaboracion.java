package ar.edu.utn.frba.dds.domain.colaboraciones;

import ar.edu.utn.frba.dds.domain.colaboraciones.calculadores.CalculadorDePuntos;
import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Colaboracion class representa una colaboracion de un colaborador.
 */
@Getter
@Setter
@AllArgsConstructor
public abstract class Colaboracion {
  protected Colaborador colaborador;
  protected CalculadorDePuntos calculadorDePuntos;
  protected LocalDate fecha;

  /**
   * Constructor por defecto.
   */
  public Colaboracion() {
  }

  /**
   * Metodo abstracto efectuar que se implementa en las clases hijas de Colaboracion.
   * Su funcion es sumar puntos al colaborador.
   */
  public void efectuar() {
    this.colaborador.sumarPuntos(this.calculadorDePuntos.calcularPuntos(this));
  }

}