package ar.edu.utn.frba.dds.domain.colaboraciones;

import ar.edu.utn.frba.dds.domain.colaboraciones.calculadores.CalculadorDePuntos;
import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Colaboracion class representa una colaboracion de un colaborador.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Colaboracion {
  protected long id;
  protected Colaborador colaborador;
  protected CalculadorDePuntos calculadorDePuntos;
  protected LocalDate fecha;

  public Colaboracion(Colaborador colaborador, CalculadorDePuntos calculadorDePuntos, LocalDate fecha) {
    this.colaborador = colaborador;
    this.calculadorDePuntos = calculadorDePuntos;
    this.fecha = fecha;
  }

  /**
   * Metodo abstracto efectuar que se implementa en las clases hijas de Colaboracion.
   * Su funcion es sumar puntos al colaborador.
   */
  public void efectuar() {
    this.colaborador.sumarPuntos(this.calculadorDePuntos.calcularPuntos(this));
  }

}