package ar.edu.utn.frba.dds.domain.colaboraciones;

import ar.edu.utn.frba.dds.domain.colaboraciones.calculadores.CalculadorDePuntosFactory;
import ar.edu.utn.frba.dds.domain.colaboraciones.calculadores.CalculadorPuntosRedistribucionVianda;
import ar.edu.utn.frba.dds.domain.colaboraciones.utils.MotivoRedistribucionVianda;
import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.domain.colaboradores.FormaColaboracion;
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
  public RedistribucionViandas(Heladera heladeraOrigen, Heladera heladeraDestino, LocalDate fecha, MotivoRedistribucionVianda motivo, Integer cantidad) {
    this.calculadorDePuntos = CalculadorDePuntosFactory.create(FormaColaboracion.REDISTRIBUCION_VIANDA);
    this.heladeraOrigen = heladeraOrigen;
    this.heladeraDestino = heladeraDestino;
    this.fecha = fecha;
    this.motivo = motivo;
    this.cantidad = cantidad;
  }

  /**
   * Constructor con parametros.
   */
  public RedistribucionViandas(Colaborador colaborador, Heladera heladeraOrigen, Heladera heladeraDestino, LocalDate fecha, MotivoRedistribucionVianda motivo, Integer cantidad) {
    super(colaborador, CalculadorDePuntosFactory.create(FormaColaboracion.REDISTRIBUCION_VIANDA), fecha);
    this.heladeraOrigen = heladeraOrigen;
    this.heladeraDestino = heladeraDestino;
    this.motivo = motivo;
    this.cantidad = cantidad;
  }

  public RedistribucionViandas() {
    this.calculadorDePuntos = CalculadorDePuntosFactory.create(FormaColaboracion.REDISTRIBUCION_VIANDA);
  }

  /**
   * Metodo efectuar que se encarga de sumar puntos al colaborador.
   */
  @Override
  public void efectuar() {
    super.efectuar();
  }


}