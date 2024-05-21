package ar.edu.utn.frba.dds.domain.colaboraciones;

import ar.edu.utn.frba.dds.domain.colaboraciones.utils.MotivoRedistribucionVianda;
import ar.edu.utn.frba.dds.domain.heladeras.Heladera;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.*;

/**
 * representa el movimiento de una vianda entre heladeras.
 */
@Getter
@AllArgsConstructor
public class RedistribucionViandas extends Colaboracion{

  /**
   * Default constructor
   */
  public RedistribucionViandas() {
  }


  @Override
  public void efectuar() {
    super.efectuar();
  }


  private Heladera heladeraOrigen;

  private Heladera heladeraDestino;


  private Date fecha;


  private MotivoRedistribucionVianda motivo;


  private Integer cantidad;

}