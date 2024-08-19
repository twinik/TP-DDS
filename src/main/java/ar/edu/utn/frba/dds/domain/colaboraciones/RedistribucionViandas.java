package ar.edu.utn.frba.dds.domain.colaboraciones;

import ar.edu.utn.frba.dds.domain.colaboraciones.calculadores.CalculadorPuntosRedistribucionVianda;
import ar.edu.utn.frba.dds.domain.colaboraciones.utils.MotivoRedistribucionVianda;
import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.domain.heladeras.Heladera;
import java.time.LocalDate;
import lombok.Getter;


/**
 * RedistribucionViandas class representa una colaboracion de un colaborador.
 * Representa el movimiento de una vianda entre heladeras.
 */
@Getter
public class RedistribucionViandas extends Colaboracion {

  /**
   * Constructor por defecto.
   */

  private Heladera heladeraOrigen;
  private Heladera heladeraDestino;
  private MotivoRedistribucionVianda motivo;
  private Integer cantidad;

  /**
   * Constructor con parametros.
   */
  public RedistribucionViandas(Heladera heladeraOrigen, Heladera heladeraDestino, LocalDate fecha, MotivoRedistribucionVianda motivo, Integer cantidad,CalculadorPuntosRedistribucionVianda calculador) {
    this.calculadorDePuntos = calculador;
    this.heladeraOrigen = heladeraOrigen;
    this.heladeraDestino = heladeraDestino;
    this.fecha = fecha;
    this.motivo = motivo;
    this.cantidad = cantidad;
  }

  /**
   * Constructor con parametros.
   */
  public RedistribucionViandas(Colaborador colaborador, Heladera heladeraOrigen, Heladera heladeraDestino, LocalDate fecha, MotivoRedistribucionVianda motivo, Integer cantidad,CalculadorPuntosRedistribucionVianda calculador) {
    super(colaborador, calculador, fecha);
    this.heladeraOrigen = heladeraOrigen;
    this.heladeraDestino = heladeraDestino;
    this.motivo = motivo;
    this.cantidad = cantidad;
  }

  public RedistribucionViandas(CalculadorPuntosRedistribucionVianda calculador) {
    this.calculadorDePuntos = calculador;
  }

  /**
   * Metodo efectuar que se encarga de sumar puntos al colaborador.
   */
  @Override
  public void efectuar() {
    super.efectuar();
  }


}