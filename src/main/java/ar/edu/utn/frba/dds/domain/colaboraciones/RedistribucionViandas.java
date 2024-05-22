package ar.edu.utn.frba.dds.domain.colaboraciones;

import ar.edu.utn.frba.dds.domain.colaboraciones.calculadores.CalculadorDePuntos;
import ar.edu.utn.frba.dds.domain.colaboraciones.calculadores.CalculadorPuntosRedistribucionVianda;
import ar.edu.utn.frba.dds.domain.colaboraciones.utils.MotivoRedistribucionVianda;
import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.domain.heladeras.Heladera;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.*;

/**
 * representa el movimiento de una vianda entre heladeras.
 */
@Getter
public class RedistribucionViandas extends Colaboracion {

  /**
   * Default constructor
   */
  public RedistribucionViandas() {
    this.calculadorDePuntos = new CalculadorPuntosRedistribucionVianda();
  }

  public RedistribucionViandas(Heladera heladeraOrigen, Heladera heladeraDestino, LocalDate fecha, MotivoRedistribucionVianda motivo, Integer cantidad) {
    this.calculadorDePuntos = new CalculadorPuntosRedistribucionVianda();
    this.heladeraOrigen = heladeraOrigen;
    this.heladeraDestino = heladeraDestino;
    this.fecha = fecha;
    this.motivo = motivo;
    this.cantidad = cantidad;
  }

  public RedistribucionViandas(Colaborador colaborador, Heladera heladeraOrigen, Heladera heladeraDestino, LocalDate fecha, MotivoRedistribucionVianda motivo, Integer cantidad) {
    super(colaborador, new CalculadorPuntosRedistribucionVianda(), fecha);
    this.heladeraOrigen = heladeraOrigen;
    this.heladeraDestino = heladeraDestino;
    this.motivo = motivo;
    this.cantidad = cantidad;
  }

  @Override
  public void efectuar() {
    super.efectuar();
  }


  private Heladera heladeraOrigen;

  private Heladera heladeraDestino;


  private MotivoRedistribucionVianda motivo;


  private Integer cantidad;

}