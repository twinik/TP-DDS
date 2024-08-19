package ar.edu.utn.frba.dds.domain.heladeras;

import ar.edu.utn.frba.dds.domain.colaboraciones.DonacionVianda;
import ar.edu.utn.frba.dds.domain.colaboraciones.calculadores.CalculadorDePuntos;
import ar.edu.utn.frba.dds.domain.colaboraciones.calculadores.CalculadorPuntosDonacionVianda;
import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import java.util.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * IngresoVianda class permite representar un ingreso de vianda.
 */
@Getter
@AllArgsConstructor
public class IngresoVianda {
  private long id;
  private Date fechaDonacion;
  private Colaborador colaborador;
  private CalculadorPuntosDonacionVianda calculador;
  private boolean entregada;
  private List<Vianda> viandas;
  private Heladera heladera;

  public List<DonacionVianda> donar() {
    return this.viandas.stream().map(vianda -> new DonacionVianda(vianda, this.colaborador,calculador)).toList();
  }

}